package com.opchaves.kommonei.users;

import java.net.URI;
import java.time.LocalDateTime;

import org.bson.types.ObjectId;
import org.jboss.logging.Logger;

import com.opchaves.kommonei.jwt.AuthRequest;
import com.opchaves.kommonei.jwt.AuthResponse;
import com.opchaves.kommonei.jwt.PBKDF2Encoder;
import com.opchaves.kommonei.jwt.TokenPayload;
import com.opchaves.kommonei.jwt.TokenUtils;

import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

@Path("/api/users")
public class UserResource {

  @Inject
  PBKDF2Encoder encoder;

  @Inject
  Logger log;

  @POST
  @Path("/register")
  public Uni<Response> register(@Valid UserRequest input) {
    User user = input.toEntity();

    return user.emailAlreadyTaken().onItem().transform(taken -> {
      if (taken) {
        throw new WebApplicationException("Email " + user.email + " already taken", Response.Status.BAD_REQUEST);
      } else {
        return user;
      }
    }).onItem().transformToUni(u -> {
      u.password = encoder.encode(u.password);
      u.createdAt = LocalDateTime.now();

      return u.<User>persist();
    }).onItem().transform(u -> {
      URI uri = URI.create("/api/users/" + u.id.toString());
      log.info("User created: " + uri);
      return Response.created(uri).entity(new UserResponse(u)).build();
    }).onFailure().recoverWithItem(e -> {
      log.error("Error creating user", e);
      var errRes = new ErrorResponse("Error creating user: " + e.getMessage(), 400);
      return Response.status(400).entity(errRes).build();
    });
  }

  @GET
  @Path("/{id}")
  public Uni<UserResponse> findUserById(String id) {
    return User.<User>findById(new ObjectId(id)).map(UserResponse::new);
  }

  @POST
  @Path("/login")
  public Uni<Response> login(@Valid AuthRequest input) {
    return User.<User>find("email", input.getEmail()).firstResult()
        .onItem().ifNotNull().transform(u -> {
          try {
            if (encoder.verify(input.getPassword(), u.password)) {
              var params = new TokenPayload();
              params.id = u.id.toString();
              params.email = u.email;
              params.roles = u.roles;
              params.issuer = "kommonei";
              // TODO: get from config properties
              params.duration = 3600000L;
              return Response.ok(new AuthResponse(TokenUtils.generateToken(params))).build();
            } else {
              log.warn("Invalid password for user " + u.email);
              var errRes = new ErrorResponse("Invalid email or password", 401);
              return Response.status(Response.Status.UNAUTHORIZED).entity(errRes).build();
            }
          } catch (Exception e) {
            log.error("Error generating token", e);
            var errRes = new ErrorResponse("Invalid email or password", 401);
            return Response.status(Response.Status.UNAUTHORIZED).entity(errRes).build();
          }
        })
        .onItem().ifNull().continueWith(() -> {
          log.warn("User not found: " + input.getEmail());
          var errRes = new ErrorResponse("Invalid email or password", 401);
          return Response.status(Response.Status.UNAUTHORIZED).entity(errRes).build();
        });
  }
}

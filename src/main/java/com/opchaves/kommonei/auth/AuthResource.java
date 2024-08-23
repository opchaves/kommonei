package com.opchaves.kommonei.auth;

import static jakarta.ws.rs.core.Response.Status.UNAUTHORIZED;

import java.net.URI;
import java.time.LocalDateTime;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import com.opchaves.kommonei.users.User;
import com.opchaves.kommonei.users.UserRequest;
import com.opchaves.kommonei.users.UserResponse;

import io.quarkus.logging.Log;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

@Path("/api/auth")
public class AuthResource {

  @Inject
  PBKDF2Encoder encoder;

  @ConfigProperty(name = "com.opchaves.kommonei.jwt.duration")
  private Long duration;

  @ConfigProperty(name = "com.opchaves.kommonei.jwt.issuer")
  private String issuer;

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
      Log.info("User created: " + uri);
      return Response.created(uri).entity(new UserResponse(u)).build();
    });
  }

  @POST
  @Path("/login")
  public Uni<AuthResponse> login(@Valid AuthRequest input) {
    return User.<User>find("email", input.getEmail()).firstResult()
        .onItem().ifNotNull().transform(u -> {
          var matches = encoder.verify(input.getPassword(), u.password);
          if (!matches) {
            Log.warn("Invalid password for user " + u.email);
            throw new WebApplicationException("Invalid email or password", UNAUTHORIZED);
          }
          var payload = new TokenPayload(u, issuer, duration);
          return new AuthResponse(TokenUtils.generateToken(payload));
        })
        .onItem().ifNull().failWith(() -> {
          Log.warn("User not found: " + input.getEmail());
          throw new WebApplicationException("Invalid email or password", UNAUTHORIZED);
        });
  }
}

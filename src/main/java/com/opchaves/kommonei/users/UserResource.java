package com.opchaves.kommonei.users;

import org.bson.types.ObjectId;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.jboss.logging.Logger;

import io.smallrye.mutiny.Uni;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;

@Path("/api/users")
public class UserResource {
  @Inject
  JsonWebToken jwt;

  @Inject
  Logger log;

  @GET
  @Path("/me")
  @RolesAllowed("USER")
  public Uni<Response> me(@Context SecurityContext ctx) {
    var userId = jwt.getClaim("sub").toString();

    return User.<User>findById(new ObjectId(userId))
        .map(user -> Response.ok(new UserResponse(user)).build());
  }

  @GET
  @Path("/{id}")
  @RolesAllowed("ADMIN")
  public Uni<UserResponse> findUserById(String id) {
    return User.<User>findById(new ObjectId(id)).map(UserResponse::new);
  }

}

package com.opchaves.kommonei.users;

import org.bson.types.ObjectId;
import org.eclipse.microprofile.jwt.Claim;
import org.eclipse.microprofile.jwt.Claims;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.jboss.logging.Logger;

import io.smallrye.mutiny.Uni;
import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

@Path("/api/users")
@RequestScoped
public class UserResource {
  @Inject
  JsonWebToken jwt;

  @Inject
  @Claim(standard = Claims.sub)
  String userId;

  @Inject
  Logger log;

  @GET
  @Path("/me")
  @RolesAllowed("USER")
  public Uni<UserResponse> me() {
    return findUserById(new ObjectId(userId));
  }

  @GET
  @Path("/{id}")
  @RolesAllowed("ADMIN")
  public Uni<UserResponse> findUserById(String id) {
    return findUserById(new ObjectId(id));
  }

  private Uni<UserResponse> findUserById(ObjectId id) {
    return User.<User>findById(new ObjectId(userId))
        .onItem().ifNull().failWith(new WebApplicationException("User not found", 404))
        .onItem().ifNotNull().transform(UserResponse::new);
  }
}

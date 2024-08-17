package com.opchaves.kommonei.activities;

import java.net.URI;
import java.time.LocalDateTime;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.eclipse.microprofile.jwt.Claim;
import org.eclipse.microprofile.jwt.Claims;

import io.smallrye.mutiny.Uni;
import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

@Path("/api/activities")
@RequestScoped
public class ActivityResource {

  @Inject
  @Claim(standard = Claims.sub)
  String userId;

  @POST
  @RolesAllowed("USER")
  public Uni<Response> createActivity(@Valid ActivityRequest input) {
    Activity activity = input.toEntity();

    activity.id = new ObjectId();
    activity.createdAt = LocalDateTime.now();
    activity.updatedAt = LocalDateTime.now();
    activity.userId = new ObjectId(userId);

    return activity.<Activity>persist()
        .onItem().transform(a -> {
          URI uri = URI.create("/api/activities/" + a.id.toString());
          return Response.created(uri).entity(new ActivityResponse(a)).build();
        });
  }

  @GET
  @RolesAllowed("USER")
  public Uni<Response> listActivities() {
    return Activity.findUserActivities(new ObjectId(userId))
        .onItem().transform(activities -> {
          var list = activities.stream().map(ActivityResponse::new).toList();
          return Response.ok(list).build();
        });
  }

  @PUT
  @Path("{id}")
  @RolesAllowed("USER")
  public Uni<Response> updateActivity(String id, @Valid ActivityRequest input) {
    var query = new Document("_id", new ObjectId(id)).append("userId", new ObjectId(userId));
    return Activity.find(query)
        .<Activity>firstResult()
        .onItem().ifNull().failWith(new WebApplicationException("Activity not found", 404))
        .onItem().ifNotNull().call(activity -> {
          activity.name = input.name;
          activity.description = input.description;
          activity.price = input.price;
          activity.paid = input.paid;
          activity.category = input.category;
          activity.updatedAt = LocalDateTime.now();

          return activity.<Activity>update();
        })
        .onItem().transform(a -> {
          return Response.ok(new ActivityResponse(a)).build();
        });
  }

  @DELETE
  @Path("{id}")
  @RolesAllowed("USER")
  public Uni<Response> deleteActivity(String id) {
    var query = new Document("_id", new ObjectId(id)).append("userId", new ObjectId(userId));
    return Activity.find(query)
        .<Activity>firstResult()
        .onItem().ifNull().failWith(new WebApplicationException("Activity not found", 404))
        .onItem().ifNotNull().transformToUni(activity -> activity.delete())
        .onItem().transform(a -> {
          return Response.noContent().build();
        });
  }
}

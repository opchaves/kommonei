package com.opchaves.kommonei.activities;

import java.net.URI;
import java.util.List;

import org.bson.types.ObjectId;
import org.eclipse.microprofile.jwt.Claim;
import org.eclipse.microprofile.jwt.Claims;

import io.smallrye.mutiny.Uni;
import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
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

  ActivityService activityService;

  public ActivityResource(ActivityService activityService) {
    this.activityService = activityService;
  }

  @GET
  @RolesAllowed("USER")
  public Uni<List<ActivityDTO>> listUserActivities() {
    return activityService.listUserActivities(new ObjectId(userId));
  }

  @GET
  @Path("all")
  @RolesAllowed("ADMIN")
  public Uni<List<ActivityDTO>> listActivities() {
    return activityService.listActivities();
  }

  @GET
  @Path("{id}")
  @RolesAllowed("USER")
  public Uni<ActivityDTO> getActivity(String id) {
    return activityService.getActivity(new ObjectId(id), new ObjectId(userId))
      .onItem().ifNull().failWith(new WebApplicationException("Activity not found", 404));
  }

  @POST
  @RolesAllowed("USER")
  public Uni<Response> createActivity(@Valid ActivityDTO input) {
    return activityService.createActivity(input, new ObjectId(userId))
        .onItem().transform(activity -> {
          URI uri = URI.create("/api/activities/" + activity.id);
          return Response.created(uri).entity(activity).build();
        });
  }

  @PUT
  @Path("{id}")
  @RolesAllowed("USER")
  public Uni<ActivityDTO> updateActivity(String id, @Valid ActivityDTO input) {
    return activityService.updateActivity(new ObjectId(id), input, new ObjectId(userId));
  }

  public Uni<Response> deleteActivity(String id) {
    return activityService.deleteActivity(new ObjectId(id), new ObjectId(userId))
        .onItem().transform(deleted -> {
          return Response.noContent().build();
        });
  }
}

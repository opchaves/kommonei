package com.opchaves.kommonei.activities;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

import java.net.URI;
import java.util.List;

import org.bson.types.ObjectId;
import org.eclipse.microprofile.jwt.Claim;
import org.eclipse.microprofile.jwt.Claims;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.ExampleObject;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import com.opchaves.kommonei.exception.ErrorExamples;
import com.opchaves.kommonei.exception.ErrorResponse;

import io.smallrye.common.annotation.RunOnVirtualThread;
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
@Tag(name = "activities")
@RequestScoped
@RunOnVirtualThread
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
  @Operation(summary = "List all activities for the current user")
  @APIResponse(responseCode = "200", description = "List of activities for the current user", content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = ActivityDTO.class), examples = @ExampleObject(name = "activities", value = ActivityExamples.VALID_EXAMPLE_ACTIVITY_LIST)))
  @APIResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = ErrorResponse.class), examples = @ExampleObject(name = "unauthorized", value = ErrorExamples.EXAMPLE_UNAUTHORIZED)))
  public List<ActivityDTO> listUserActivities() {
    return activityService.listUserActivities(new ObjectId(userId));
  }

  @GET
  @Path("all")
  @RolesAllowed("ADMIN")
  public List<ActivityDTO> listActivities() {
    return activityService.listActivities();
  }

  @GET
  @Path("{id}")
  @RolesAllowed("USER")
  public ActivityDTO getActivity(String id) {
    return activityService
      .getActivty(id)
      .orElseThrow(() -> new WebApplicationException("Activity not found", 404));
  }

  @POST
  @RolesAllowed("USER")
  public Response createActivity(@Valid ActivityDTO input) {
    ActivityDTO dto = activityService.createActivity(input, userId);
    URI uri = URI.create("/api/activities/" + dto.id);
    return Response.created(uri).entity(dto).build();
  }

  @PUT
  @Path("{id}")
  @RolesAllowed("USER")
  public ActivityDTO updateActivity(String id, @Valid ActivityDTO input) {
    return activityService.updateActivity(input, id, userId);
  }

  public Response deleteActivity(String id) {
    activityService.deleteActivity(id, userId);
    return Response.noContent().build();
  }
}

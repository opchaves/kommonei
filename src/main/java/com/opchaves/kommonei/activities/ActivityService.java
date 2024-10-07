package com.opchaves.kommonei.activities;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.bson.Document;
import org.bson.types.ObjectId;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.WebApplicationException;

@ApplicationScoped
public class ActivityService {

  private final ActivityMapper activityMapper;

  public ActivityService(ActivityMapper activityMapper) {
    this.activityMapper = activityMapper;
  }

  public List<ActivityDTO> listActivities() {
    return Activity.<Activity>listAll()
        .stream()
        .map(activityMapper::toDTO)
        .toList();
  }

  public List<ActivityDTO> listUserActivities(Object userId) {
    return Activity.<Activity>list("userId", userId)
        .stream()
        .map(activityMapper::toDTO)
        .toList();
  }

  public Optional<ActivityDTO> getActivty(String id) {
    return Activity.<Activity>findByIdOptional(new ObjectId(id)).map(activityMapper::toDTO);
  }

  public Optional<ActivityDTO> getActivity(String id, String userId) {
    var query = new Document("_id", new ObjectId(id))
        .append("userId", new ObjectId(userId));
    return Activity.<Activity>find(query)
        .firstResultOptional()
        .map(activityMapper::toDTO);
  }

  public ActivityDTO createActivity(ActivityDTO input, String userId) {
    var activity = activityMapper.toEntity(input);
    activity.userId = new ObjectId(userId);
    activity.createdAt = LocalDateTime.now();
    activity.updatedAt = LocalDateTime.now();
    activity.persist();
    return activityMapper.toDTO(activity);
  }

  public ActivityDTO updateActivity(ActivityDTO input, String id, String userId) {
    return this.getActivity(id, userId)
        .map(a -> {
          var activity = activityMapper.toEntity(input);
          activity.id = new ObjectId(a.id);
          activity.userId = new ObjectId(a.userId);
          activity.createdAt = a.createdAt;
          activity.updatedAt = LocalDateTime.now();
          activity.persist();
          return activityMapper.toDTO(activity);
        })
        .orElseThrow(() -> new WebApplicationException("Activity not found", 404));
  }

  public void deleteActivity(String id, String userId) {
    var query = new Document("_id", new ObjectId(id)).append("userId", new ObjectId(userId));
    Activity.delete(query);
  }
}

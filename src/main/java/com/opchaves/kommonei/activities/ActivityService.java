package com.opchaves.kommonei.activities;

import java.time.LocalDateTime;
import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ActivityService {

  private final ActivityMapper activityMapper;

  public ActivityService(ActivityMapper activityMapper) {
    this.activityMapper = activityMapper;
  }

  public Uni<List<ActivityDTO>> listActivities() {
    return Activity.<Activity>listAll().onItem().transform(activities -> {
      return activities.stream().map(activityMapper::toDTO).toList();
    });
  }

  public Uni<List<ActivityDTO>> listUserActivities(Object userId) {
    return Activity.<Activity>find("userId", userId).list().onItem().transform(activities -> {
      return activities.stream().map(activityMapper::toDTO).toList();
    });
  }

  public Uni<ActivityDTO> getActivty(ObjectId id) {
    return Activity.<Activity>findById(id)
        .onItem()
        .ifNotNull()
        .transform(activityMapper::toDTO);
  }

  public Uni<ActivityDTO> getActivity(ObjectId id, ObjectId userId) {
    var query = new Document("_id", id).append("userId", userId);
    return Activity.find(query)
        .<Activity>firstResult()
        .onItem().ifNotNull().transform(activityMapper::toDTO);
  }

  public Uni<ActivityDTO> createActivity(ActivityDTO input, ObjectId userId) {
    var activity = activityMapper.toEntity(input);
    activity.userId = userId;
    activity.createdAt = LocalDateTime.now();
    activity.updatedAt = LocalDateTime.now();
    return activity.<Activity>persist().onItem().transform(activityMapper::toDTO);
  }

  public Uni<ActivityDTO> updateActivity(ObjectId id, ActivityDTO input, ObjectId userId) {
    var query = new Document("_id", id).append("userId", userId);
    return Activity.find(query)
        .<Activity>firstResult()
        .onItem().ifNotNull().call(a -> {
          var activity = activityMapper.toEntity(input);

          activity.id = a.id;
          activity.userId = a.userId;
          activity.createdAt = a.createdAt;
          activity.updatedAt = LocalDateTime.now();

          return activity.<Activity>update();
        })
        .onItem().transform(activityMapper::toDTO);
  }

  public Uni<Boolean> deleteActivity(ObjectId id, ObjectId userId) {
    var query = new Document("_id", id).append("userId", userId);
    return Activity.delete(query).onItem().transform(deleted -> deleted > 0);
  }
}

package com.opchaves.kommonei.activities;

import java.time.LocalDateTime;
import java.util.List;

import org.bson.types.ObjectId;

import io.quarkus.mongodb.panache.common.MongoEntity;
import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoEntity;
import io.smallrye.mutiny.Uni;

@MongoEntity(collection = "users")
public class Activity extends ReactivePanacheMongoEntity {
  public String name;
  public String description;
  public Double price;
  public Boolean paid;
  public String category;
  public ObjectId userId;
  public LocalDateTime handledAt;
  public LocalDateTime createdAt;
  public LocalDateTime updatedAt;

  public Activity() {
  }

  public Activity(String name, String description, Double price, Boolean paid, String category, ObjectId userId) {
    this.name = name;
    this.description = description;
    this.price = price;
    this.paid = paid;
    this.category = category;
    this.userId = userId;
    this.handledAt = LocalDateTime.now();
    this.createdAt = LocalDateTime.now();
    this.updatedAt = LocalDateTime.now();
  }

  public static Uni<List<Activity>> findUserActivities(ObjectId userId) {
    return Activity.find("userId", userId).list();
  }
}

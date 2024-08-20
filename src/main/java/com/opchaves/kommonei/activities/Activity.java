package com.opchaves.kommonei.activities;

import java.time.LocalDateTime;

import org.bson.types.ObjectId;

import io.quarkus.mongodb.panache.common.MongoEntity;
import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoEntity;

@MongoEntity(collection = "activities")
public class Activity extends ReactivePanacheMongoEntity {
  public String name;
  public String description;
  public Double price;
  public Boolean paid;
  public String type;
  public String category;
  public ObjectId userId;
  public LocalDateTime handledAt;
  public LocalDateTime createdAt;
  public LocalDateTime updatedAt;

  public Activity() {
  }
}

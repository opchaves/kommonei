package com.opchaves.kommonei.activities;

import java.time.LocalDateTime;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(description = "Activity Response Object")
public class ActivityResponse {
  @Schema(required = true, example = "507f1f77bcf86cd799439011", description = "Activity Object Id")
  public String id;

  @Schema(required = true, description = "Activity name")
  public String name;

  @Schema(required = true, description = "Activity description")
  public String description;

  @Schema(required = true, description = "Activity price")
  public Double price;

  @Schema(required = true, description = "Activity payment status")
  public Boolean paid;

  @Schema(required = true, description = "Activity category")
  public String category;

  @Schema(required = true, description = "User Object Id")
  public String userId;

  @Schema(required = true, description = "Activity handling date")
  public LocalDateTime handledAt;

  @Schema(required = true, description = "Activity creation date")
  public LocalDateTime createdAt;

  @Schema(required = true, description = "Activity update date")
  public LocalDateTime updatedAt;

  public ActivityResponse() {
  }

  public ActivityResponse(Activity activity) {
    this.id = activity.id.toString();
    this.name = activity.name;
    this.description = activity.description;
    this.price = activity.price;
    this.paid = activity.paid;
    this.category = activity.category;
    this.userId = activity.userId.toString();
    this.handledAt = activity.handledAt;
    this.createdAt = activity.createdAt;
    this.updatedAt = activity.updatedAt;
  }
}

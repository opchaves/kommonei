package com.opchaves.kommonei.activities;

import java.time.LocalDateTime;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import jakarta.validation.constraints.NotBlank;

@Schema(name = "ActivityRequest", description = "Activity request")
public class ActivityRequest {

  @Schema(required = true)
  @NotBlank(message = "Name is required")
  public String name;

  @Schema(required = false)
  public String description;

  @Schema(defaultValue = "0.0", required = false)
  public Double price = 0.0;

  @Schema(defaultValue = "false", required = false)
  public Boolean paid = false;

  @Schema(required = true)
  @NotBlank(message = "Category is required")
  public String category;

  @Schema(name = "handledAt", required = false)
  public LocalDateTime handledAt = LocalDateTime.now();

  public ActivityRequest() {
  }

  public ActivityRequest(String name, String description, Double price, Boolean paid, String category,
      LocalDateTime handledAt) {
    this.name = name;
    this.description = description;
    this.price = price;
    this.paid = paid;
    this.category = category;
    this.handledAt = handledAt;
  }

  public Activity toEntity() {
    Activity activity = new Activity();
    activity.name = this.name;
    activity.description = this.description;
    activity.price = this.price;
    activity.paid = this.paid;
    activity.category = this.category;
    activity.handledAt = this.handledAt;
    return activity;
  }
}

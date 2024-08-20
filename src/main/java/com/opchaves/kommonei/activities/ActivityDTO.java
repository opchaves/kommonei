
package com.opchaves.kommonei.activities;

import java.time.LocalDateTime;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import jakarta.validation.constraints.NotBlank;

@Schema(name = "ActivityDTO", description = "Activity input data")
public class ActivityDTO {
  @Schema(example = "507f1f77bcf86cd799439011", description = "Activity Object Id")
  public String id;

  @Schema(required = true)
  @NotBlank(message = "Name is required")
  public String name;

  @Schema()
  public String description;

  @Schema(defaultValue = "0.0", minimum = "0.0")
  public Double price;

  @Schema(defaultValue = "false")
  public Boolean paid;

  @Schema(required = true, enumeration = { "income", "expense" })
  @NotBlank(message = "Type is required")
  public String type;

  @Schema(required = true)
  public String category;

  @Schema(example = "507f1f77bcf86cd799439011", description = "User Object Id")
  public String userId;

  @Schema(name = "handledAt")
  public LocalDateTime handledAt;

  @Schema
  public LocalDateTime createdAt;

  @Schema
  public LocalDateTime updatedAt;

  public ActivityDTO() {
  }
}

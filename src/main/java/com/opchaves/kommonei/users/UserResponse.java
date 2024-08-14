package com.opchaves.kommonei.users;

import java.time.LocalDateTime;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(description = "User Response Object")
public class UserResponse {

  @Schema(required = true, example = "507f1f77bcf86cd799439011", description = "User Object Id")
  public String id;

  @Schema(required = true, example = "John Doe", description = "User name")
  public String name;

  @Schema(required = true, example = "jonhdoe@example.com", description = "User email")
  public String email;

  @Schema(required = true, example = "2024-08-14T11:43:42.63617", description = "User creation date")
  public String createdAt;

  public UserResponse() {
  }

  public UserResponse(String id, String name, String email) {
    this.id = id;
    this.name = name;
    this.email = email;
  }

  public UserResponse(User user) {
    this.id = user.id.toString();
    this.name = user.name;
    this.email = user.email;
    this.createdAt = user.createdAt.toString();
  }
}

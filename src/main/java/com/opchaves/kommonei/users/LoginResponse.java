package com.opchaves.kommonei.users;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(description = "Login Response Object")
public class LoginResponse {

  @Schema(required = true, example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...", description = "JWT Token")
  public String token;

  public LoginResponse() {
  }

  public LoginResponse(String token) {
    this.token = token;
  }
}

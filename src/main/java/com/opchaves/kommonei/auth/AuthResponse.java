package com.opchaves.kommonei.auth;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(name = "AuthResponse")
public class AuthResponse {

  @Schema(required = true, example = "eyJhbGci...")
  public String token;

  public AuthResponse() {
  }

  public AuthResponse(String token) {
    this.token = token;
  }
}

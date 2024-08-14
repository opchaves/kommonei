package com.opchaves.kommonei.users;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(name = "ErrorResponse")
public class ErrorResponse {

  @Schema(required = true, example = "Email already taken", description = "Error message")
  public String message;

  @Schema(required = true, example = "400", description = "HTTP status code")
  public Integer status;

  public ErrorResponse() {
  }

  public ErrorResponse(String message, Integer status) {
    this.message = message;
    this.status = status;
  }
}

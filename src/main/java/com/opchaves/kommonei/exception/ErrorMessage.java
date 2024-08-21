package com.opchaves.kommonei.exception;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class ErrorMessage {
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String path;
  private String message;

  public ErrorMessage(String path, String message) {
    this.path = path;
    this.message = message;
  }

  public ErrorMessage(String message) {
    this(null, message);
  }

  public ErrorMessage() {
  }
}

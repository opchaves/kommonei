package com.opchaves.kommonei.exception;

import com.fasterxml.jackson.annotation.JsonInclude;

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

  public String getPath() {
    return path;
  }

  public String getMessage() {
    return message;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((path == null) ? 0 : path.hashCode());
    result = prime * result + ((message == null) ? 0 : message.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    ErrorMessage other = (ErrorMessage) obj;
    if (path == null) {
      if (other.path != null)
        return false;
    } else if (!path.equals(other.path))
      return false;
    if (message == null) {
      if (other.message != null)
        return false;
    } else if (!message.equals(other.message))
      return false;
    return true;
  }
}

package com.opchaves.kommonei.exception;

import java.util.List;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * ErrorResponse
 *
 * @see https://developers.redhat.com/articles/2022/03/03/rest-api-error-modeling-quarkus-20
 */
@Schema(name = "ErrorResponse", description = "Error response")
public class ErrorResponse {

  @Schema(description = "Unique error identifier")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String errorId;

  @Schema(description = "List of error messages")
  private List<ErrorMessage> errors;

  public ErrorResponse(String errorId, ErrorMessage errorMessage) {
    this.errorId = errorId;
    this.errors = List.of(errorMessage);
  }

  public ErrorResponse(ErrorMessage errorMessage) {
    this(null, errorMessage);
  }

  public ErrorResponse(List<ErrorMessage> errors) {
    this.errorId = null;
    this.errors = errors;
  }

  public ErrorResponse() {
  }

  public String getErrorId() {
    return errorId;
  }

  public List<ErrorMessage> getErrors() {
    return errors;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((errorId == null) ? 0 : errorId.hashCode());
    result = prime * result + ((errors == null) ? 0 : errors.hashCode());
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
    ErrorResponse other = (ErrorResponse) obj;
    if (errorId == null) {
      if (other.errorId != null)
        return false;
    } else if (!errorId.equals(other.errorId))
      return false;
    if (errors == null) {
      if (other.errors != null)
        return false;
    } else if (!errors.equals(other.errors))
      return false;
    return true;
  }

}

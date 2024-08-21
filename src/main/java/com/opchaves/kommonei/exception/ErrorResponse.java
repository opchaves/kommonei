package com.opchaves.kommonei.exception;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * ErrorResponse
 *
 * @see https://developers.redhat.com/articles/2022/03/03/rest-api-error-modeling-quarkus-20
 */
@Getter
@EqualsAndHashCode
public class ErrorResponse {
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String errorId;
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
}

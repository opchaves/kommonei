package com.opchaves.kommonei.exception;

import static jakarta.ws.rs.core.Response.Status.BAD_REQUEST;

import java.util.List;
import java.util.stream.Collectors;

import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ConstraintViolationExceptionMapper
    implements ExceptionMapper<ConstraintViolationException> {

  public Response toResponse(ConstraintViolationException e) {
    List<ErrorMessage> errors = e.getConstraintViolations()
        .stream()
        .map(violation -> {
          String message = violation.getMessage();
          String path = violation.getPropertyPath().toString();
          return new ErrorMessage(message, path);
        })
        .collect(Collectors.toList());
    ErrorResponse errorResponse = new ErrorResponse(errors);
    return Response.status(BAD_REQUEST).entity(errorResponse).build();
  }
}

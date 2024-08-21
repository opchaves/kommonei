package com.opchaves.kommonei.exception;

import java.util.ResourceBundle;
import java.util.UUID;

import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Provider
public class ThrowableMapper implements ExceptionMapper<Throwable> {

  public Response toResponse(Throwable e) {
    String errorId = UUID.randomUUID().toString();

    if (e instanceof WebApplicationException) {
      Response originalResponse = ((WebApplicationException) e).getResponse();
      ErrorMessage errorMessage = new ErrorMessage(e.getMessage());
      return Response.fromResponse(originalResponse).entity(errorMessage).build();
    }

    if (e instanceof IllegalArgumentException) {
      ErrorMessage errorMessage = new ErrorMessage(e.getMessage());
      ErrorResponse errorResponse = new ErrorResponse(errorId, errorMessage);
      return Response.status(Response.Status.BAD_REQUEST).entity(errorResponse).build();
    }

    log.error("errorId[{}]", errorId, e);

    String defaultErrorMessage = ResourceBundle.getBundle("ValidationMessages").getString("System.error");
    ErrorMessage errorMessage = new ErrorMessage(defaultErrorMessage);
    ErrorResponse errorResponse = new ErrorResponse(errorId, errorMessage);

    return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(errorResponse).build();
  }

}

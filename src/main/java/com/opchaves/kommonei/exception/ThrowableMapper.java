package com.opchaves.kommonei.exception;

import java.util.ResourceBundle;
import java.util.UUID;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Provider
public class ThrowableMapper implements ExceptionMapper<Throwable> {

  public Response toResponse(Throwable e) {
    String errorId = UUID.randomUUID().toString();
    log.error("errorId[{}]", errorId, e);
    String defaultErrorMessage = ResourceBundle.getBundle("ValidationMessages").getString("System.error");
    ErrorMessage errorMessage = new ErrorMessage(defaultErrorMessage);
    ErrorResponse errorResponse = new ErrorResponse(errorId, errorMessage);
    return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(errorResponse).build();
  }

}

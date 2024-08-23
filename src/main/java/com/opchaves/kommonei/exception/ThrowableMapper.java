package com.opchaves.kommonei.exception;

import java.util.ResourceBundle;
import java.util.UUID;

import io.quarkus.logging.Log;
import io.smallrye.jwt.build.JwtException;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ThrowableMapper implements ExceptionMapper<Throwable> {

  public Response toResponse(Throwable e) {
    String errorId = UUID.randomUUID().toString();

    if (e instanceof WebApplicationException) {
      Log.warn("errorId[{}]", errorId, e);
      Response originalResponse = ((WebApplicationException) e).getResponse();
      ErrorMessage errorMessage = new ErrorMessage(e.getMessage());
      return Response.fromResponse(originalResponse).entity(errorMessage).build();
    }

    if (e instanceof IllegalArgumentException) {
      Log.warn("errorId[{}]", errorId, e);
      // TODO: set an generic message in prod for illegal argument errors
      ErrorMessage errorMessage = new ErrorMessage(e.getMessage());
      ErrorResponse errorResponse = new ErrorResponse(errorId, errorMessage);
      return Response.status(Response.Status.BAD_REQUEST).entity(errorResponse).build();
    }

    if (e instanceof JwtException) {
      Log.warn("errorId[{}]", errorId, e);
      // TODO: set an generic message in prod for jwt errors
      ErrorMessage errorMessage = new ErrorMessage(e.getMessage());
      ErrorResponse errorResponse = new ErrorResponse(errorId, errorMessage);
      return Response.status(Response.Status.UNAUTHORIZED).entity(errorResponse).build();
    }

    Log.error("errorId[{}]", errorId, e);

    String defaultErrorMessage = ResourceBundle.getBundle("ValidationMessages").getString("System.error");
    ErrorMessage errorMessage = new ErrorMessage(defaultErrorMessage);
    ErrorResponse errorResponse = new ErrorResponse(errorId, errorMessage);

    return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(errorResponse).build();
  }

}

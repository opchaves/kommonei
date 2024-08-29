package com.opchaves.kommonei.exception;

public final class ErrorExamples {
  private ErrorExamples() {
    throw new IllegalStateException("Utility class");
  }

  public static final String EXAMPLE_UNAUTHORIZED = """
      {
        "code": 401,
        "message": "Unauthorized"
      }
      """;

  public static final String EXAMPLE_INTERNAL_SERVER_ERROR = """
      {
        "code": 500,
        "message": "Internal Server Error"
      }
      """;
}

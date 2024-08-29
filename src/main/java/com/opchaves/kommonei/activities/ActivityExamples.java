package com.opchaves.kommonei.activities;

final class ActivityExamples {
  private ActivityExamples() {
    throw new IllegalStateException("Utility class");
  }

  static final String VALID_EXAMPLE_ACTIVITY = """
        {
          "id": "5f9b3b7b7f4b3b1b3c7b3b7b",
          "name": "Activity 1",
          "description": "Description 1",
          "price": 0.0,
          "paid": true,
          type": "income",
          "category": "Category 1",
          "userId": "5f9b3b7b7f4b3b1b3c7b3b7b",
          "handledAt": "2024-05-30T00:00:00Z",
          "createdAt": "2024-05-30T00:00:00Z",
          "updatedAt": "2024-05-30T00:00:00Z"
        }
      """;

  static final String VALID_EXAMPLE_ACTIVITY_LIST = "[" + VALID_EXAMPLE_ACTIVITY + "]";
}

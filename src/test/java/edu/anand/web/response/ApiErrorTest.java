package edu.anand.web.response;

import static edu.anand.web.response.ApiError.Severity.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ApiErrorTest {

  @Test
  void apiError_AllParams() {
    ApiError apiError = new ApiError(404, "Record Not Found", ERROR, new RuntimeException());

    assertAll(
        () -> assertEquals(404, apiError.code()),
        () -> assertEquals("Record Not Found", apiError.message()),
        () -> assertEquals(ERROR, apiError.severity()),
        () -> assertEquals(RuntimeException.class, apiError.cause().getClass()));
  }

  @Test
  void apiError_NoException() {
    ApiError apiError = new ApiError(199, "Process took over 5 seconds", WARNING);

    assertAll(
        () -> assertEquals(199, apiError.code()),
        () -> assertEquals("Process took over 5 seconds", apiError.message()),
        () -> assertEquals(WARNING, apiError.severity()),
        () -> assertNull(apiError.cause()));
  }

  @Test
  void apiError_NoSeverity() {
    ApiError apiError = new ApiError(200, "Job already running");

    assertAll(
        () -> assertEquals(200, apiError.code()),
        () -> assertEquals("Job already running", apiError.message()),
        () -> assertEquals(INFO, apiError.severity()),
        () -> assertNull(apiError.cause()));
  }
}

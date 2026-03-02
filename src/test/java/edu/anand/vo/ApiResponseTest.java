package edu.anand.vo;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.Test;

class ApiResponseTest {

  @Test
  void apiResponse_OnlyData() {
    ApiResponse<String> apiResponse = new ApiResponse<>("Test");

    assertAll(
        () -> assertEquals("Test", apiResponse.data()),
        () -> assertEquals(200, apiResponse.statusCode()),
        () -> assertNull(apiResponse.statusMessage()),
        () -> assertNull(apiResponse.errors()),
        () -> assertFalse(apiResponse.hasErrors()));
  }

  @Test
  void apiResponse_Data_Message() {
    ApiResponse<String> apiResponse = new ApiResponse<>("Test", "Success");

    assertAll(
        () -> assertEquals("Test", apiResponse.data()),
        () -> assertEquals(200, apiResponse.statusCode()),
        () -> assertEquals("Success", apiResponse.statusMessage()),
        () -> assertNull(apiResponse.errors()),
        () -> assertFalse(apiResponse.hasErrors()));
  }

  @Test
  void apiResponse_Data_Code_Message() {
    ApiResponse<String> apiResponse = new ApiResponse<>("Test", 200, "Job completed successfully");

    assertAll(
        () -> assertEquals("Test", apiResponse.data()),
        () -> assertEquals(200, apiResponse.statusCode()),
        () -> assertEquals("Job completed successfully", apiResponse.statusMessage()),
        () -> assertNull(apiResponse.errors()),
        () -> assertFalse(apiResponse.hasErrors()));
  }

  @Test
  void apiResponse_With_Errors() {
    ApiError error = new ApiError(404, "User [username] not found", ApiError.Severity.ERROR);
    ApiResponse<String> apiResponse = new ApiResponse<>(404, "Data Not Found", List.of(error));

    assertAll(
        () -> assertNull(apiResponse.data()),
        () -> assertEquals(404, apiResponse.statusCode()),
        () -> assertEquals("Data Not Found", apiResponse.statusMessage()),
        () -> assertEquals(1, apiResponse.errors().size()),
        () -> assertTrue(apiResponse.hasErrors()));
  }
}

package edu.anand.vo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ApiPageableRequestTest {

  @Test
  void apiPageableRequest() {
    ApiPageableRequest<String> request =
        new ApiPageableRequest<>(3, 10, "DTO with filter criteria");
    assertAll(
        () -> assertEquals(3, request.page()),
        () -> assertEquals(10, request.rows()),
        () -> assertEquals("DTO with filter criteria", request.criteria()));
  }
}

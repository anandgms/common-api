package edu.anand.web.response;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.Test;

class ApiPageableResponseTest {
  private static final List<String> ITEMS = List.of("Item1", "Item2");

  @Test
  void apiPageableResponse_Items_Page_Rows() {
    ApiPageableResponse<String> response = new ApiPageableResponse<>(ITEMS, 2, 10);
    assertAll(
        () -> assertArrayEquals(new String[] {"Item1", "Item2"}, response.items().toArray()),
        () -> assertEquals(200, response.statusCode()),
        () -> assertNull(response.statusMessage()),
        () -> assertEquals(2, response.page()),
        () -> assertEquals(10, response.rows()),
        () -> assertFalse(response.hasErrors()));
  }

  @Test
  void apiPageableResponse_Items_Page_Rows_Code_Message() {
    ApiPageableResponse<String> response =
        new ApiPageableResponse<>(ITEMS, 8, 5, 200, "Search results for User");
    assertAll(
        () -> assertArrayEquals(new String[] {"Item1", "Item2"}, response.items().toArray()),
        () -> assertEquals(200, response.statusCode()),
        () -> assertEquals("Search results for User", response.statusMessage()),
        () -> assertEquals(8, response.page()),
        () -> assertEquals(5, response.rows()),
        () -> assertFalse(response.hasErrors()));
  }

  @Test
  void apiPageableResponse_Errors() {
    ApiError error = new ApiError(500, "Bad request : Username required");
    ApiPageableResponse<String> response =
        new ApiPageableResponse<>(ITEMS, 8, 5, 500, "Search failed", List.of(error));
    assertAll(
        () -> assertArrayEquals(new String[] {"Item1", "Item2"}, response.items().toArray()),
        () -> assertEquals(500, response.statusCode()),
        () -> assertEquals("Search failed", response.statusMessage()),
        () -> assertEquals(8, response.page()),
        () -> assertEquals(5, response.rows()),
        () -> assertTrue(response.hasErrors()),
        () -> assertEquals(1, response.errors().size()));
  }
}

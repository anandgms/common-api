package edu.anand.web.response;

import java.util.List;

public record ApiPageableResponse<T>(
    List<T> items,
    int page,
    int rows,
    int statusCode,
    String statusMessage,
    List<ApiError> errors) {

  public ApiPageableResponse(List<T> items, int page, int rows) {
    this(items, page, rows, 200, null, null);
  }

  public ApiPageableResponse(
      List<T> items, int page, int rows, int statusCode, String statusMessage) {
    this(items, page, rows, statusCode, statusMessage, null);
  }

  public boolean hasErrors() {
    return errors != null && !errors().isEmpty();
  }
}

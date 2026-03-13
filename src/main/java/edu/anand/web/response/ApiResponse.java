package edu.anand.web.response;

import java.util.List;

public record ApiResponse<T>(T data, int statusCode, String statusMessage, List<ApiError> errors) {

  public ApiResponse(T data, int statusCode, String statusMessage) {
    this(data, statusCode, statusMessage, null);
  }

  public ApiResponse(T data, String statusMessage) {
    this(data, 200, statusMessage, null);
  }

  public ApiResponse(T data) {
    this(data, 200, null, null);
  }

  public ApiResponse(int statusCode, String statusMessage, List<ApiError> errors) {
    this(null, statusCode, statusMessage, errors);
  }

  public boolean hasErrors() {
    return errors != null && !errors().isEmpty();
  }
}

package edu.anand.web.response;

public record ApiError(int code, String message, Severity severity, Throwable cause) {

  enum Severity {
    ERROR,
    WARNING,
    INFO
  }

  public ApiError(int code, String message, Severity severity) {
    this(code, message, severity, null);
  }

  public ApiError(int code, String message) {
    this(code, message, Severity.INFO, null);
  }
}

package edu.anand.logging;

import java.util.function.Supplier;

/** Provides methods to handle all logging */
public interface AppLogger {

  /** Logs the message if TRACE level logging is enabled. */
  void trace(Supplier<String> message);

  /** Logs the message, replacing placeholders {} with value from the arguments. */
  void trace(Supplier<String> message, Object... args);

  /** Logs the message if INFO level logging is enabled. */
  void info(Supplier<String> message);

  /** Logs the message, replacing placeholders {} with value from the arguments. */
  void info(Supplier<String> message, Object... args);

  /** Logs the message if DEBUG level logging is enabled. */
  void debug(Supplier<String> message);

  /** Logs the message, replacing placeholders {} with value from the arguments. */
  void debug(Supplier<String> message, Object... args);

  /** Logs the message if WARNING level logging is enabled. */
  void warn(Supplier<String> message);

  /** Logs the message, replacing placeholders {} with value from the arguments. */
  void warn(Supplier<String> message, Object... args);

  /** Logs the message if ERROR level logging is enabled. */
  void error(Supplier<String> message);

  /** Logs the error message and root cause. */
  void error(Supplier<String> message, Throwable throwable);

  /** Logs the message, replacing placeholders {} with value from the arguments. */
  void error(Supplier<String> message, Object... args);

  /** Logs username, user roles, and the message. */
  void audit(Supplier<String> message);

  /**
   * Logs username, user roles, and the message, replacing placeholders {} with value from the
   * arguments.
   */
  void audit(Supplier<String> message, Object... args);

  /**
   * Adds data to Mapped Diagnostic Context (MDC) Entries in the MDC will automatically be prepended
   * to all log messages that are recorded for a HTTP request.
   */
  AppLogger with(String key, String value);
}

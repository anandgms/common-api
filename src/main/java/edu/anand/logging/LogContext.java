package edu.anand.logging;

import org.slf4j.MDC;

/** Thread-safe data container for Mapped Diagnostic Context (MDC) Entries */
public class LogContext {

  public static void put(String key, Object value) {
    MDC.put(key, String.valueOf(value));
  }

  public static void remove(String key) {
    MDC.remove(key);
  }

  public static void clear() {
    MDC.clear();
  }
}

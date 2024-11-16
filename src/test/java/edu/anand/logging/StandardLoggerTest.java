package edu.anand.logging;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

public class StandardLoggerTest {

  @Test
  void getLogger() {
    StandardLogger standardLogger = StandardLogger.getLogger("test_file");
    assertNotNull(standardLogger);

    standardLogger.error(() -> "Error");
    standardLogger.warn(() -> "Warning");
    standardLogger.info(() -> "Info");
    standardLogger.debug(() -> "Debug");
    standardLogger.trace(() -> "Trace");
  }

  @Test
  void getLogger_VarArgs() {
    StandardLogger standardLogger = StandardLogger.getLogger("test_file");
    assertNotNull(standardLogger);

    standardLogger.error(() -> "Error {}", "test");
    standardLogger.warn(() -> "Warning {}", "test");
    standardLogger.info(() -> "Info {}", "test");
    standardLogger.debug(() -> "Debug {}", "test");
    standardLogger.trace(() -> "Trace {}", "test");
  }
}

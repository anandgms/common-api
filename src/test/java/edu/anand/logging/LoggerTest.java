package edu.anand.logging;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

public class LoggerTest {

  @Test
  void getLogger() {
    Logger logger = Logger.getLogger("test_file");
    assertNotNull(logger);

    logger.error(() -> "Error");
    logger.warn(() -> "Warning");
    logger.info(() -> "Info");
    logger.debug(() -> "Debug");
    logger.trace(() -> "Trace");
  }

  @Test
  void getLogger_VarArgs() {
    Logger logger = Logger.getLogger("test_file");
    assertNotNull(logger);

    logger.error(() -> "Error {}", "test");
    logger.warn(() -> "Warning {}", "test");
    logger.info(() -> "Info {}", "test");
    logger.debug(() -> "Debug {}", "test");
    logger.trace(() -> "Trace {}", "test");
  }
}

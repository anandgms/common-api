package edu.anand.logging;

import java.util.UUID;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class AppLoggerTest {

  private final AppLogger logger = new AppLoggerFactory().getLogger(AppLoggerTest.class);

  @BeforeAll
  static void setup(){
    LogContext.put("traceId", UUID.randomUUID().toString());
    LogContext.put("userId", "currentUser");
    LogContext.put("roles", "po, scrum_master, developer");
  }

  @AfterAll
  static void cleanup(){
    LogContext.clear();
  }

  @Test
  void getLogger() {
    logger.error(() -> "Error");
    logger.warn(() -> "Warning");
    logger.info(() -> "Info");
    logger.debug(() -> "Debug");
    logger.trace(() -> "Trace");
  }

  @Test
  void getLogger_VarArgs() {
    logger.error(() -> "Error {}", "argument #1");
    logger.warn(() -> "Warning {}", "argument #1");
    logger.info(() -> "Info {}", "argument #1");
    logger.debug(() -> "Debug {}", "argument #1");
    logger.trace(() -> "Trace {}", "argument #1");
  }

  @Test
  void getLogger_CanHandleNulls() {
    logger.error(null);
    logger.warn(null);
    logger.info(null);
    logger.debug(null);
    logger.trace(null);
  }

  @Test
  void testMDC() {
    logger.with("userId", "anand").with("roles", "admin, developer");
    logger.info(() -> "User logged in");
  }

  @Test
  void userIsNull() {
    logger.audit(() -> "Audit Message");
  }

  @Test
  void validUser() {
    logger.audit(() -> "Audit Message");
  }

  @Test
  void validUser_VarArgs() {
    logger.audit(() -> "Audit Message with variable {}", "var1");
  }
}

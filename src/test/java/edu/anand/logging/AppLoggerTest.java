package edu.anand.logging;

import org.junit.jupiter.api.Test;

// import org.springframework.security.core.authority.SimpleGrantedAuthority;
// import org.springframework.security.core.userdetails.User;

class AppLoggerTest {

  private final AppLogger logger = new AppLoggerFactory().getLogger(AppLoggerTest.class);

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

  // @Test
  // void userRoles_NullNotAllowed() {
  //   assertThrowsExactly(IllegalArgumentException.class, () -> new User("testUser", "", null));
  // }

  @Test
  void userRolesIsEmpty() {
    // User user = new User("testUser", "", Collections.emptyList());
    logger.audit(() -> "Audit Message");
  }

  @Test
  void multipleUserRoles() {
    // User user =
    //     new User(
    //         "testUser",
    //         "",
    //         List.of(
    //             new SimpleGrantedAuthority("ADMIN"),
    //             new SimpleGrantedAuthority("USER"),
    //             new SimpleGrantedAuthority("MANAGER")));
    logger.audit(() -> "Audit Message");
  }

  @Test
  void validUser() {
    // User user = new User("testUser", "", List.of(new SimpleGrantedAuthority("ADMIN")));
    logger.audit(() -> "Audit Message");
  }

  @Test
  void validUser_VarArgs() {
    // User user = new User("testUser", "", List.of(new SimpleGrantedAuthority("ADMIN")));
    logger.audit(() -> "Audit Message with variable {}", "var1");
  }
}

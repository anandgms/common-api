package edu.anand.logging;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

class AuditLoggerTest {

  @Test
  void userIsNull() {
    AuditLogger auditLogger = AuditLogger.getLogger("test_file");
    assertNotNull(auditLogger);

    auditLogger.audit(null, () -> "Error");
  }

  @Test
  void userRoles_NullNotAllowed() {
    assertThrowsExactly(IllegalArgumentException.class, () -> new User("testUser", "", null));
  }

  @Test
  void userRolesIsEmpty() {
    AuditLogger auditLogger = AuditLogger.getLogger("test_file");
    assertNotNull(auditLogger);

    User user = new User("testUser", "", Collections.emptyList());
    auditLogger.audit(user, () -> "Info message");
  }

  @Test
  void multipleUserRoles() {
    AuditLogger auditLogger = AuditLogger.getLogger("test_file");
    assertNotNull(auditLogger);

    User user =
        new User(
            "testUser",
            "",
            List.of(
                new SimpleGrantedAuthority("ADMIN"),
                new SimpleGrantedAuthority("USER"),
                new SimpleGrantedAuthority("MANAGER")));
    auditLogger.audit(user, () -> "Info message");
  }

  @Test
  void validUser() {
    AuditLogger auditLogger = AuditLogger.getLogger("test_file");
    assertNotNull(auditLogger);

    User user = new User("testUser", "", List.of(new SimpleGrantedAuthority("ADMIN")));
    auditLogger.audit(user, () -> "Info message");
  }

  @Test
  void validUser_VarArgs() {
    AuditLogger auditLogger = AuditLogger.getLogger("test_file");
    assertNotNull(auditLogger);

    User user = new User("testUser", "", List.of(new SimpleGrantedAuthority("ADMIN")));
    auditLogger.audit(user, () -> "Info message with variable {} ", "var1");
  }
}

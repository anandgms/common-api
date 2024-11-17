package edu.anand.logging;

import java.util.function.Supplier;
import java.util.stream.Collectors;
import org.springframework.security.core.userdetails.UserDetails;

public class AuditLogger {

  private final org.slf4j.Logger LOGGER;

  public static AuditLogger getLogger(String name) {
    return new AuditLogger(name);
  }

  protected AuditLogger(String name) {
    LOGGER = org.slf4j.LoggerFactory.getLogger(name);
  }

  public <T extends UserDetails> void audit(T user, Supplier<String> message) {
    if (LOGGER.isInfoEnabled() && message != null) {
      LOGGER.info(message.get() + getUsernameAndRoles(user));
    }
  }

  public <T extends UserDetails> void audit(T user, Supplier<String> message, Object... objects) {
    if (LOGGER.isInfoEnabled() && message != null) {
      LOGGER.info(message.get() + getUsernameAndRoles(user), objects);
    }
  }

  private String getUsernameAndRoles(UserDetails user) {
    if (user == null) return "Anonymous User";

    String username = user.getUsername();
    String roles =
        user.getAuthorities().stream().map(String::valueOf).collect(Collectors.joining(", "));

    return " User [" + username + "] with roles [" + roles + "]";
  }
}

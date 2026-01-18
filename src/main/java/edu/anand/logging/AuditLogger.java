package edu.anand.logging;

import java.util.function.Supplier;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;

public class AuditLogger {

  private final Logger LOGGER;

  public static AuditLogger getLogger(String name) {
    return new AuditLogger(name);
  }

  protected AuditLogger(String name) {
    LOGGER = LoggerFactory.getLogger(name);
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

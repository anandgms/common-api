package edu.anand.web.filter;

import edu.anand.logging.AppLogger;
import edu.anand.logging.AppLoggerFactory;
import edu.anand.logging.LogContext;
import edu.anand.web.security.CurrentUser;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/** Logs all requests received by the web-service. */
@Component
public class RequestLoggingFilter extends OncePerRequestFilter {

  public static final String CORRELATION_ID = "traceId";

  private final AppLogger logger;

  public RequestLoggingFilter(AppLoggerFactory appLoggerFactory) {
    this.logger = appLoggerFactory.getLogger(RequestLoggingFilter.class);
  }

  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {

    long start = System.currentTimeMillis();

    setTraceId(request);

    setUsernameAndRoles();

    try {
      filterChain.doFilter(request, response);

      long duration = System.currentTimeMillis() - start;

      logger.info(
          () -> "HTTP {} {} status={} duration={}ms",
          request.getMethod(),
          request.getRequestURI(),
          response.getStatus(),
          duration);

    } finally {
      LogContext.clear();
    }
  }

  private void setTraceId(HttpServletRequest request) {
    String traceId =
        Optional.ofNullable(request.getHeader("X-Trace-Id")).orElse(UUID.randomUUID().toString());
    LogContext.put(CORRELATION_ID, traceId);
  }

  private void setUsernameAndRoles() {
    String username = CurrentUser.username();
    LogContext.put("userId", ObjectUtils.getIfNull(username, "Unknown"));

    Collection<String> roles = ObjectUtils.getIfNull(CurrentUser.roles(), Collections.emptyList());
    LogContext.put("roles", roles);
    roles.stream().collect(Collectors.joining(", "));
  }
}

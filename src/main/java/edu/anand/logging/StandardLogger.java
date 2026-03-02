package edu.anand.logging;

import java.util.function.Supplier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StandardLogger {

  private final Logger LOGGER;

  public static StandardLogger getLogger(String name) {
    return new StandardLogger(name);
  }

  protected StandardLogger(String name) {
    LOGGER = LoggerFactory.getLogger(name);
  }

  public void trace(Supplier<String> message) {
    if (LOGGER.isTraceEnabled() && message != null) {
      LOGGER.trace(message.get());
    }
  }

  public void trace(Supplier<String> message, Object... objects) {
    if (LOGGER.isTraceEnabled() && message != null) {
      LOGGER.trace(message.get(), objects);
    }
  }

  public void debug(Supplier<String> message) {
    if (LOGGER.isDebugEnabled() && message != null) {
      LOGGER.debug(message.get());
    }
  }

  public void debug(Supplier<String> message, Object... objects) {
    if (LOGGER.isDebugEnabled() && message != null) {
      LOGGER.debug(message.get(), objects);
    }
  }

  public void info(Supplier<String> message) {
    if (LOGGER.isInfoEnabled() && message != null) {
      LOGGER.info(message.get());
    }
  }

  public void info(Supplier<String> message, Object... objects) {
    if (LOGGER.isInfoEnabled() && message != null) {
      LOGGER.info(message.get(), objects);
    }
  }

  public void warn(Supplier<String> message) {
    if (LOGGER.isWarnEnabled() && message != null) {
      LOGGER.warn(message.get());
    }
  }

  public void warn(Supplier<String> message, Object... objects) {
    if (LOGGER.isWarnEnabled() && message != null) {
      LOGGER.warn(message.get(), objects);
    }
  }

  public void error(Supplier<String> message) {
    if (LOGGER.isErrorEnabled() && message != null) {
      LOGGER.error(message.get());
    }
  }

  public void error(Supplier<String> message, Object... objects) {
    if (LOGGER.isErrorEnabled() && message != null) {
      LOGGER.error(message.get(), objects);
    }
  }
}

package edu.anand.logging;

import java.util.function.Supplier;

public class Logger {

  private final org.slf4j.Logger LOGGER;

  public static Logger getLogger(String name) {
    return new Logger(name);
  }

  protected Logger(String name) {
    LOGGER = org.slf4j.LoggerFactory.getLogger(name);
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

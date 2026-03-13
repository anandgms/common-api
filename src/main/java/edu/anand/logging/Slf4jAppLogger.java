package edu.anand.logging;

import java.util.function.Supplier;
import org.slf4j.Logger;
import org.slf4j.MDC;

/**
 * Default SL4J implentation of AppLogger. 
 * Works with both logback and log4j2.
 */
public class Slf4jAppLogger implements AppLogger {

  private final Logger logger;
  private final Logger auditLogger;

  public Slf4jAppLogger(Logger logger, Logger auditLogger) {
    this.logger = logger;
    this.auditLogger = auditLogger;
  }

  @Override
  public void trace(Supplier<String> message) {
    if (logger.isTraceEnabled() && message != null) {
      logger.trace(message.get());
    }
  }

  @Override
  public void trace(Supplier<String> message, Object... args) {
    if (logger.isTraceEnabled() && message != null) {
      logger.trace(message.get(), args);
    }
  }

  @Override
  public void info(Supplier<String> message) {
    if (logger.isInfoEnabled() && message != null) {
      logger.info(message.get());
    }
  }

  @Override
  public void info(Supplier<String> message, Object... args) {
    if (logger.isInfoEnabled() && message != null) {
      logger.info(message.get(), args);
    }
  }

  @Override
  public void debug(Supplier<String> message) {
    if (logger.isDebugEnabled() && message != null) {
      logger.debug(message.get());
    }
  }

  @Override
  public void debug(Supplier<String> message, Object... args) {
    if (logger.isDebugEnabled() && message != null) {
      logger.debug(message.get(), args);
    }
  }

  @Override
  public void warn(Supplier<String> message) {
    if (logger.isWarnEnabled() && message != null) {
      logger.warn(message.get());
    }
  }

  @Override
  public void warn(Supplier<String> message, Object... args) {
    if (logger.isWarnEnabled() && message != null) {
      logger.warn(message.get(), args);
    }
  }

  @Override
  public void error(Supplier<String> message) {
    if (logger.isErrorEnabled() && message != null) {
      logger.error(message.get());
    }
  }

  @Override
  public void error(Supplier<String> message, Throwable throwable) {
    if (logger.isErrorEnabled() && message != null) {
      logger.error(message.get(), throwable);
    }
  }

  @Override
  public void error(Supplier<String> message, Object... args) {
    if (logger.isErrorEnabled() && message != null) {
      logger.error(message.get(), args);
    }
  }

  @Override
  public void audit(Supplier<String> message) {
    if (message != null) {
      auditLogger.info(message.get());
    }
  }

  @Override
  public void audit(Supplier<String> message, Object... args) {
    if (message != null) {
      auditLogger.info(message.get(), args);
    }
  }

  @Override
  public AppLogger with(String key, String value) {
    MDC.put(key, value);
    return this;
  }
}

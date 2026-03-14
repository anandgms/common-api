package edu.anand.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/** Factory class to create instances of AppLogger */
@Component
public class AppLoggerFactory {

  public AppLogger getLogger(Class<?> clazz) {
    Logger logger = LoggerFactory.getLogger(clazz);
    return new Slf4jAppLogger(logger);
  }
}

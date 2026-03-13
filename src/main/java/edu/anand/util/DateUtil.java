package edu.anand.util;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import org.apache.commons.lang3.time.DateUtils;

public final class DateUtil {

  public static final LocalDate toLocalDate(Date date) {
    return DateUtils.toLocalDateTime(date).toLocalDate();
  }

  public static final ZonedDateTime toZonedDateTime(Date date) {
    return DateUtils.toZonedDateTime(date);
  }

  public static final Date toDate(LocalDate localDate) {
    return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
  }

  public static final Date toDate(ZonedDateTime zonedDateTime) {
    return Date.from(zonedDateTime.toInstant());
  }
}

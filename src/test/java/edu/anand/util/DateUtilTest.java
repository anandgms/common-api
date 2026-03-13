package edu.anand.util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import org.junit.jupiter.api.Test;

public class DateUtilTest {

  @Test
  void testToDate() {
    LocalDate localDate = LocalDate.now();
    Date date = DateUtil.toDate(localDate);
    assertNotNull(date);
    assertEquals(localDate, DateUtil.toLocalDate(date));
  }

  @Test
  void testToDate2() {
    ZonedDateTime zonedDateTime = ZonedDateTime.now(ZoneId.systemDefault());
    Date date = DateUtil.toDate(zonedDateTime);
    assertNotNull(date);
    assertThat(zonedDateTime.toInstant())
        .isCloseTo(date.toInstant(), within(1, ChronoUnit.SECONDS));
  }

  @Test
  void testToLocalDate() {
    Date date = new Date();
    LocalDate localDate = DateUtil.toLocalDate(date);
    assertNotNull(localDate);

    DateTimeFormatter YYYY_MM_DD = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    String returnValue = localDate.format(YYYY_MM_DD);
    String legacyDate = date.toInstant().atZone(ZoneId.systemDefault()).format(YYYY_MM_DD);
    assertEquals(legacyDate, returnValue);
  }

  @Test
  void testToZonedDateTime() {
    Date date = new Date();
    ZonedDateTime zonedDateTime = DateUtil.toZonedDateTime(date);
    assertNotNull(zonedDateTime);
    assertEquals(date.toInstant(), zonedDateTime.toInstant());
  }

  @Test
  void testToLocalDate2() {
    Date date = new Date();
    LocalDate localDate = DateUtil.toLocalDate(date);
    assertNotNull(localDate);
    assertTrue(localDate.isBefore(LocalDate.now().plusDays(1)));
  }

  @Test
  void testToDateFromZonedDateTime() {
    ZonedDateTime zonedDateTime = ZonedDateTime.now(ZoneId.of("UTC"));
    Date date = DateUtil.toDate(zonedDateTime);
    assertNotNull(date);
    assertThat(zonedDateTime.toInstant())
        .isCloseTo(date.toInstant(), within(1, ChronoUnit.SECONDS));
  }

  @Test
  void testRoundTripLocalDate() {
    LocalDate originalDate = LocalDate.of(2023, 5, 15);
    Date intermediate = DateUtil.toDate(originalDate);
    LocalDate result = DateUtil.toLocalDate(intermediate);
    assertEquals(originalDate, result);
  }

  @Test
  void testRoundTripZonedDateTime() {
    ZonedDateTime original = ZonedDateTime.of(2023, 5, 15, 10, 30, 0, 0, ZoneId.systemDefault());
    Date intermediate = DateUtil.toDate(original);
    ZonedDateTime result = DateUtil.toZonedDateTime(intermediate);
    assertEquals(original.toInstant(), result.toInstant());
  }

  @Test
  void testToDateWithNullLocalDate() {
    assertThrows(NullPointerException.class, () -> DateUtil.toDate((LocalDate) null));
  }

  @Test
  void testToLocalDateWithNull() {
    assertThrows(NullPointerException.class, () -> DateUtil.toLocalDate(null));
  }
}

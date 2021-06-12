package io.assalielmehdi.keynote.validators;

import java.time.LocalDateTime;

public interface CommonValidator {

  void requireNotNull(Object object, String name);

  void requireInRangeClosed(int minValue, int maxValue, int value, String name);

  void requireNotBlank(String value, String name);

  void requireLengthInRange(int minLength, int maxLength, String value, String name);

  void requireDateAfterMaxDays(LocalDateTime date, int maxDays, String name);

  void requireEmail(String email);

}

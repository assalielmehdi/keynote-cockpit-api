package io.assalielmehdi.keynote.validators;

public interface CommonValidator {

  void requireNotNull(Object object, String name);

  void requireInRangeClosed(int minValue, int maxValue, int value, String name);

  void requireNotBlank(String value, String name);

  void requireLengthInRange(int minLength, int maxLength, String value, String name);

}

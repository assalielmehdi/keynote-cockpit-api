package io.assalielmehdi.keynote.validators;

import io.assalielmehdi.keynote.exceptions.BadInputException;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CommonValidatorImpl implements CommonValidator {

  @Override
  public void requireNotNull(Object object, String name) {
    if (object == null) {
      throw new BadInputException(String.format("Bad Input: %s should be not null.", name));
    }
  }

  @Override
  public void requireInRangeClosed(int minValue, int maxValue, int value, String name) {
    if (value < minValue || value > maxValue) {
      throw new BadInputException(String.format("Bad Input: %s should be in range [%d, %d].", name, minValue, maxValue));
    }
  }

  @Override
  public void requireNotBlank(String value, String name) {
    if (value == null || value.isBlank()) {
      throw new BadInputException(String.format("Bad Input: %s should be not empty.", name));
    }
  }

  @Override
  public void requireLengthInRange(int minLength, int maxLength, String value, String name) {
    if (value == null || value.length() < minLength || value.length() > maxLength) {
      throw new BadInputException(String.format("Bad Input: %s should be have length in range [%d, %d].", name, minLength, maxLength));
    }
  }

  @Override
  public void requireDateAfterMaxDays(LocalDateTime date, int maxDays, String name) {
    if (date == null || LocalDateTime.now().plusDays(maxDays).compareTo(date) < 0) {
      throw new BadInputException(String.format("Bad Input: %s should come after %d days maximum", name, maxDays));
    }
  }

}

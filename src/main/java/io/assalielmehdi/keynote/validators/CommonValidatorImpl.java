package io.assalielmehdi.keynote.validators;

import io.assalielmehdi.keynote.exceptions.BadInputException;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.regex.Pattern;

@Component
public class CommonValidatorImpl implements CommonValidator {

  private static final String VALID_EMAIL_REGEX = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";

  private static final Pattern VALID_EMAIL_PATTERN = Pattern.compile(VALID_EMAIL_REGEX);

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

  @Override
  public void requireEmail(String email) {
    if (email == null || !VALID_EMAIL_PATTERN.matcher(email).matches()) {
      throw new BadInputException(String.format("Bad Input: %s is not a valid email", email));
    }
  }

}

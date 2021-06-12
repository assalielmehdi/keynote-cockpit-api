package io.assalielmehdi.keynote.validators;

import io.assalielmehdi.keynote.constants.UserValidationConstants;
import io.assalielmehdi.keynote.dto.UserDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserValidatorImpl implements UserValidator {

  private final CommonValidator commonValidator;

  @Override
  public void validateRequest(UserDto userDto) {
    validateEmail(userDto.getEmail());
    validateName(userDto.getName());
    validatePassword(userDto.getPassword());
  }

  private void validateEmail(String email) {
    commonValidator.requireEmail(email);
  }

  private void validateName(String name) {
    commonValidator.requireLengthInRange(
      UserValidationConstants.MIN_NAME_LENGTH,
      UserValidationConstants.MAX_NAME_LENGTH,
      name,
      "name"
    );
  }

  private void validatePassword(String password) {
    commonValidator.requireLengthInRange(
      UserValidationConstants.MIN_PASSWORD_LENGTH,
      UserValidationConstants.MAX_PASSWORD_LENGTH,
      password,
      "password"
    );
  }

}

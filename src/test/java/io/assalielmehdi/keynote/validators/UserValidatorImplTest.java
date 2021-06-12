package io.assalielmehdi.keynote.validators;

import io.assalielmehdi.keynote.constants.UserValidationConstants;
import io.assalielmehdi.keynote.dto.UserDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserValidatorImplTest {

  @Mock
  private CommonValidator commonValidator;

  @InjectMocks
  private UserValidatorImpl userValidator;

  @Test
  void given_dto_should_check_correctly_when_validateRequest() {
    // Given
    final var email = "email";
    final var name = "name";
    final var password = "password";
    final var userDto = UserDto.builder()
      .email(email)
      .name(name)
      .password(password)
      .build();

    doNothing().when(commonValidator).requireEmail(email);
    doNothing().when(commonValidator).requireLengthInRange(
      UserValidationConstants.MIN_NAME_LENGTH,
      UserValidationConstants.MAX_NAME_LENGTH,
      name,
      "name"
    );
    doNothing().when(commonValidator).requireLengthInRange(
      UserValidationConstants.MIN_PASSWORD_LENGTH,
      UserValidationConstants.MAX_PASSWORD_LENGTH,
      password,
      "password"
    );

    // When
    userValidator.validateRequest(userDto);

    // Then
    verify(commonValidator).requireEmail(email);
    verify(commonValidator).requireLengthInRange(
      UserValidationConstants.MIN_NAME_LENGTH,
      UserValidationConstants.MAX_NAME_LENGTH,
      name,
      "name"
    );
    verify(commonValidator).requireLengthInRange(
      UserValidationConstants.MIN_PASSWORD_LENGTH,
      UserValidationConstants.MAX_PASSWORD_LENGTH,
      password,
      "password"
    );
    verifyNoMoreInteractions(commonValidator);
  }

}
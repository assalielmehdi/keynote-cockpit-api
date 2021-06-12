package io.assalielmehdi.keynote.validators;

import io.assalielmehdi.keynote.dto.UserDto;

public interface UserValidator {

  void validateRequest(UserDto userDto);

}

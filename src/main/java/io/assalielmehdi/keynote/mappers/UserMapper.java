package io.assalielmehdi.keynote.mappers;

import io.assalielmehdi.keynote.dto.UserDto;
import io.assalielmehdi.keynote.models.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserMapper {

  UserDetails toUserDetails(User user);

  User fromDto(UserDto userDto);

}

package io.assalielmehdi.keynote.services;

import io.assalielmehdi.keynote.dto.UserDto;
import io.assalielmehdi.keynote.models.User;

import java.util.List;

public interface UserService {

  List<User> getAll();

  UserDto create(UserDto userDto);

}

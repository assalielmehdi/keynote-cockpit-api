package io.assalielmehdi.keynote.services;

import io.assalielmehdi.keynote.dto.UserDto;
import io.assalielmehdi.keynote.mappers.UserMapper;
import io.assalielmehdi.keynote.models.User;
import io.assalielmehdi.keynote.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserMapper userMapper;

  private final UserRepository userRepository;

  @Override
  public List<User> getAll() {
    return userRepository.findAll();
  }

  @Override
  public UserDto create(UserDto userDto) {
    // TODO: Validate userDto
    var user = userMapper.fromDto(userDto);
    userRepository.save(user);

    return userDto;
  }

}

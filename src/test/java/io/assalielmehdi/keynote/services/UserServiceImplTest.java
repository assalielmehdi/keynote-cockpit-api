package io.assalielmehdi.keynote.services;

import io.assalielmehdi.keynote.dto.UserDto;
import io.assalielmehdi.keynote.exceptions.BadInputException;
import io.assalielmehdi.keynote.mappers.UserMapper;
import io.assalielmehdi.keynote.models.User;
import io.assalielmehdi.keynote.repositories.UserRepository;
import io.assalielmehdi.keynote.validators.UserValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

  @Mock
  private UserMapper userMapper;

  @Mock
  private UserRepository userRepository;

  @Mock
  private UserValidator userValidator;

  @InjectMocks
  private UserServiceImpl userService;

  @Test
  void should_call_repository_findAll_when_getAll() {
    // Given
    when(userRepository.findAll()).thenReturn(List.of());

    // When
    userService.getAll();

    // Then
    verify(userRepository).findAll();
  }

  @Test
  void given_not_valid_dto_should_not_save_when_create() {
    // Given
    final var userDto = UserDto.builder().build();

    doThrow(BadInputException.class).when(userValidator).validateRequest(userDto);

    // When
    assertThrows(BadInputException.class, () -> userService.create(userDto));

    // Then
    verify(userValidator).validateRequest(userDto);
    verifyNoInteractions(userMapper);
    verifyNoInteractions(userRepository);
  }

  @Test
  void given_valid_dto_should_map_and_save_when_create() {
    // Given
    final var userDto = UserDto.builder().build();
    final var user = User.builder().build();

    doNothing().when(userValidator).validateRequest(userDto);
    when(userMapper.fromDto(userDto)).thenReturn(user);
    when(userRepository.save(user)).thenReturn(user);

    // When
    userService.create(userDto);

    // Then
    verify(userValidator).validateRequest(userDto);
    verify(userMapper).fromDto(userDto);
    verify(userRepository).save(user);
  }

}
package io.assalielmehdi.keynote.controllers;

import io.assalielmehdi.keynote.dto.UserDto;
import io.assalielmehdi.keynote.models.User;
import io.assalielmehdi.keynote.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/users")
public class UserController {

  private final UserService userService;

  @GetMapping
  public List<User> getAll() {
    return userService.getAll();
  }

  @PostMapping
  public UserDto create(@RequestBody UserDto userDto) {
    return userService.create(userDto);
  }

}

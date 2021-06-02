package io.assalielmehdi.keynote.mappers;

import io.assalielmehdi.keynote.dto.UserDto;
import io.assalielmehdi.keynote.models.User;
import io.assalielmehdi.keynote.security.Authority;
import io.assalielmehdi.keynote.security.CustomPasswordEncoder;
import io.assalielmehdi.keynote.security.CustomUserDetails;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class UserMapperImpl implements UserMapper {

  private final CustomPasswordEncoder encoder;

  @Override
  public UserDetails toUserDetails(User user) {
    final var grantedAuthorities = user.getAuthorities().stream()
      .map(authority -> (GrantedAuthority) new SimpleGrantedAuthority(authority))
      .collect(Collectors.toList());

    return new CustomUserDetails(user.getEmail(), user.getPassword(), grantedAuthorities);
  }

  @Override
  public User fromDto(UserDto userDto) {
    return User.builder()
      .email(userDto.getEmail())
      .password(encoder.encode(userDto.getPassword()))
      .name(userDto.getName())
      .authorities(List.of(Authority.USER.toString()))
      .build();
  }

}

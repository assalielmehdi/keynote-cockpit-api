package io.assalielmehdi.keynote.security;

import io.assalielmehdi.keynote.mappers.UserMapper;
import io.assalielmehdi.keynote.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

  private final UserRepository userRepository;

  private final UserMapper userMapper;

  @Override
  public UserDetails loadUserByUsername(String email) {
    return userRepository
      .findByEmail(email)
      .map(userMapper::toUserDetails)
      .orElseThrow(() -> new UsernameNotFoundException(email));
  }

}

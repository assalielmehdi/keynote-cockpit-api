package io.assalielmehdi.keynote.security;

import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

  @Override
  public String getPrincipalEmail() {
    return getPrincipal().getEmail();
  }

  private CustomUserDetails getPrincipal() {
    return (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
  }

}

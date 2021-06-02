package io.assalielmehdi.keynote.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
      .headers().frameOptions().disable()
      .and()
      .formLogin().permitAll()
      .and()
      .authorizeRequests()
      .antMatchers("/api/users/**").hasAuthority(Authority.ADMIN.toString())
      .anyRequest().authenticated()
      .and()
      .csrf().disable();
  }

}

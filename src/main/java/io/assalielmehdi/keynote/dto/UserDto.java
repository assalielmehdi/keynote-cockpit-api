package io.assalielmehdi.keynote.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserDto {

  private String name;

  private String email;

  private String password;

}

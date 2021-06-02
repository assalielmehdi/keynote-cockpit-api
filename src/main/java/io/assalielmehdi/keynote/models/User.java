package io.assalielmehdi.keynote.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import javax.validation.constraints.NotNull;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class User {

  @JsonIgnore
  @Id
  private String id;

  @NotNull
  private String name;

  @NotNull
  @Indexed(unique = true)
  private String email;

  @NotNull
  private String password;

  @NotNull
  private List<String> authorities;

}
package io.assalielmehdi.keynote.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {

  @JsonIgnore
  @Id
  private String id;

  @NotNull
  private String name;

  @NotNull
  private String email;

}
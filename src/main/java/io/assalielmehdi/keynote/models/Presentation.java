package io.assalielmehdi.keynote.models;

import lombok.*;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Presentation {

  @Id
  private String id;

  @NotNull
  private String title;

  @NotNull
  private User owner;

  @NotNull
  private LocalDateTime beginsAt;

  @NotNull
  private Integer duration;

  @NotNull
  private String token;

}

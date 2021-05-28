package io.assalielmehdi.keynote.dto;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class PresentationDto {

  private String title;

  private LocalDateTime beginsAt;

  private int duration;

  private String token;

}

package io.assalielmehdi.keynote.mappers;

import io.assalielmehdi.keynote.dto.PresentationDto;
import io.assalielmehdi.keynote.exceptions.ServerErrorException;
import io.assalielmehdi.keynote.models.Presentation;
import org.springframework.stereotype.Component;

@Component
public class PresentationMapperImpl implements PresentationMapper {

  @Override
  public Presentation fromDto(PresentationDto presentationDto) {
    if (presentationDto == null) {
      throw new ServerErrorException("Cannot map null dto to model.");
    }

    return Presentation.builder()
      .title(presentationDto.getTitle())
      .beginsAt(presentationDto.getBeginsAt())
      .duration(presentationDto.getDuration())
      .token(presentationDto.getToken())
      .build();
  }

  @Override
  public PresentationDto toDto(Presentation presentation) {
    if (presentation == null) {
      throw new ServerErrorException("Cannot map null model to dto.");
    }

    return PresentationDto.builder()
      .title(presentation.getTitle())
      .beginsAt(presentation.getBeginsAt())
      .duration(presentation.getDuration())
      .token(presentation.getToken())
      .build();
  }

}

package io.assalielmehdi.keynote.mappers;

import io.assalielmehdi.keynote.dto.PresentationDto;
import io.assalielmehdi.keynote.models.Presentation;

public interface PresentationMapper {

  Presentation fromDto(PresentationDto presentationDto);

  PresentationDto toDto(Presentation presentation);

}

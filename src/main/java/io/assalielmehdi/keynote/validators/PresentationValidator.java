package io.assalielmehdi.keynote.validators;

import io.assalielmehdi.keynote.dto.PresentationDto;

public interface PresentationValidator {

  void validateRequest(PresentationDto presentationDto);

}

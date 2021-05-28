package io.assalielmehdi.keynote.validators;

import io.assalielmehdi.keynote.constants.PresentationValidatorConstants;
import io.assalielmehdi.keynote.dto.PresentationDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@AllArgsConstructor
public class PresentationValidatorImpl implements PresentationValidator {

  private final CommonValidator commonValidator;

  private void validateTitle(String title) {
    commonValidator.requireLengthInRange(
      PresentationValidatorConstants.MIN_TITLE_LENGTH,
      PresentationValidatorConstants.MAX_TITLE_LENGTH,
      title,
      "title"
    );
  }

  private void validateBeginsAt(LocalDateTime beginsAt) {
    commonValidator.requireNotNull(beginsAt, "beginsAt");
  }

  private void validateDuration(int duration) {
    commonValidator.requireInRangeClosed(
      PresentationValidatorConstants.MIN_DURATION,
      PresentationValidatorConstants.MAX_DURATION,
      duration,
      "duration"
    );
  }

  // TODO: Check beginsAt (comes after 30 days maximum)
  @Override
  public void validateRequest(PresentationDto presentationDto) {
    validateTitle(presentationDto.getTitle());
    validateBeginsAt(presentationDto.getBeginsAt());
    validateDuration(presentationDto.getDuration());
  }

}
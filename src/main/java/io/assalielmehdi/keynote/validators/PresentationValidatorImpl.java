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
    commonValidator.requireDateAfterMaxDays(
      beginsAt,
      PresentationValidatorConstants.MAX_BEGINS_AT_DAYS_AFTER,
      "beginsAt"
    );
  }

  private void validateDuration(int duration) {
    commonValidator.requireInRangeClosed(
      PresentationValidatorConstants.MIN_DURATION,
      PresentationValidatorConstants.MAX_DURATION,
      duration,
      "duration"
    );
  }

  @Override
  public void validateRequest(PresentationDto presentationDto) {
    validateTitle(presentationDto.getTitle());
    validateBeginsAt(presentationDto.getBeginsAt());
    validateDuration(presentationDto.getDuration());
  }

}
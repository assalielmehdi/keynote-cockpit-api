package io.assalielmehdi.keynote.validators;

import io.assalielmehdi.keynote.constants.PresentationValidatorConstants;
import io.assalielmehdi.keynote.dto.PresentationDto;
import io.assalielmehdi.keynote.exceptions.BadInputException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.ArgumentMatcher;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.intThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PresentationValidatorImplTest {

  @Mock
  private CommonValidator commonValidator;

  @InjectMocks
  private PresentationValidatorImpl presentationValidator;

  @Test
  void given_null_title_should_throw_bad_input_exception_when_validateRequest() {
    // Given
    final var presentationDto = PresentationDto.builder()
      .title(null)
      .beginsAt(LocalDateTime.now())
      .duration(60)
      .build();

    doThrow(BadInputException.class).when(commonValidator).requireLengthInRange(
      PresentationValidatorConstants.MIN_TITLE_LENGTH,
      PresentationValidatorConstants.MAX_TITLE_LENGTH,
      null,
      "title"
    );

    assertThrows(
      // Then
      BadInputException.class,
      // When
      () -> presentationValidator.validateRequest(presentationDto)
    );

    verify(commonValidator).requireLengthInRange(
      PresentationValidatorConstants.MIN_TITLE_LENGTH,
      PresentationValidatorConstants.MAX_TITLE_LENGTH,
      null,
      "title"
    );
    verifyNoMoreInteractions(commonValidator);
  }

  @ParameterizedTest
  @ValueSource(strings = {"", "  ", "     "})
  void given_blank_title_should_throw_bad_input_exception_when_validateRequest(final String title) {
    // Given
    final var presentationDto = PresentationDto.builder()
      .title(title)
      .beginsAt(LocalDateTime.now())
      .duration(60)
      .build();

    doThrow(BadInputException.class).when(commonValidator).requireLengthInRange(
      PresentationValidatorConstants.MIN_TITLE_LENGTH,
      PresentationValidatorConstants.MAX_TITLE_LENGTH,
      title,
      "title"
    );

    assertThrows(
      // Then
      BadInputException.class,
      // When
      () -> presentationValidator.validateRequest(presentationDto)
    );

    verify(commonValidator).requireLengthInRange(
      PresentationValidatorConstants.MIN_TITLE_LENGTH,
      PresentationValidatorConstants.MAX_TITLE_LENGTH,
      title,
      "title"
    );
    verifyNoMoreInteractions(commonValidator);
  }

  @Test
  void given_too_short_title_should_throw_bad_input_exception_when_validateRequest() {
    // Given
    final var title = "too-short";
    final var presentationDto = PresentationDto.builder()
      .title(title)
      .beginsAt(LocalDateTime.now())
      .duration(60)
      .build();

    doThrow(BadInputException.class).when(commonValidator).requireLengthInRange(
      PresentationValidatorConstants.MIN_TITLE_LENGTH,
      PresentationValidatorConstants.MAX_TITLE_LENGTH,
      title,
      "title"
    );

    assertThrows(
      // Then
      BadInputException.class,
      // When
      () -> presentationValidator.validateRequest(presentationDto)
    );

    verify(commonValidator).requireLengthInRange(
      PresentationValidatorConstants.MIN_TITLE_LENGTH,
      PresentationValidatorConstants.MAX_TITLE_LENGTH,
      title,
      "title"
    );
    verifyNoMoreInteractions(commonValidator);
  }

  @Test
  void given_too_long_title_should_throw_bad_input_exception_when_validateRequest() {
    // Given
    final var title = "too-long";
    final var presentationDto = PresentationDto.builder()
      .title(title)
      .beginsAt(LocalDateTime.now())
      .duration(60)
      .build();

    doThrow(BadInputException.class).when(commonValidator).requireLengthInRange(
      PresentationValidatorConstants.MIN_TITLE_LENGTH,
      PresentationValidatorConstants.MAX_TITLE_LENGTH,
      title,
      "title"
    );

    assertThrows(
      // Then
      BadInputException.class,
      // When
      () -> presentationValidator.validateRequest(presentationDto)
    );

    verify(commonValidator).requireLengthInRange(
      PresentationValidatorConstants.MIN_TITLE_LENGTH,
      PresentationValidatorConstants.MAX_TITLE_LENGTH,
      title,
      "title"
    );
    verifyNoMoreInteractions(commonValidator);
  }

  @Test
  void given_null_beginsAt_should_throw_bad_input_exception_when_validateRequest() {
    // Given
    final var title = "presentation";
    final var presentationDto = PresentationDto.builder()
      .title(title)
      .beginsAt(null)
      .duration(60)
      .build();

    doNothing().when(commonValidator).requireLengthInRange(
      PresentationValidatorConstants.MIN_TITLE_LENGTH,
      PresentationValidatorConstants.MAX_TITLE_LENGTH,
      title,
      "title"
    );
    doThrow(BadInputException.class).when(commonValidator).requireNotNull(null, "beginsAt");

    assertThrows(
      // Then
      BadInputException.class,
      // When
      () -> presentationValidator.validateRequest(presentationDto)
    );

    verify(commonValidator).requireLengthInRange(
      PresentationValidatorConstants.MIN_TITLE_LENGTH,
      PresentationValidatorConstants.MAX_TITLE_LENGTH,
      title,
      "title"
    );
    verify(commonValidator).requireNotNull(null, "beginsAt");
    verifyNoMoreInteractions(commonValidator);
  }

  @ParameterizedTest
  @ValueSource(ints = {4, 11})
  void given_not_in_range_duration_should_throw_bad_input_exception_when_validateRequest(final int duration) {
    // Given
    final var title = "presentation";
    final var beginsAt = LocalDateTime.now();
    final var presentationDto = PresentationDto.builder()
      .title(title)
      .beginsAt(beginsAt)
      .duration(duration)
      .build();

    ArgumentMatcher<Integer> notInRange = value -> value < 5 || value > 10;

    doNothing().when(commonValidator).requireLengthInRange(
      PresentationValidatorConstants.MIN_TITLE_LENGTH,
      PresentationValidatorConstants.MAX_TITLE_LENGTH,
      title,
      "title"
    );
    doNothing().when(commonValidator).requireNotNull(beginsAt, "beginsAt");
    doThrow(BadInputException.class).when(commonValidator).requireInRangeClosed(
      eq(PresentationValidatorConstants.MIN_DURATION),
      eq(PresentationValidatorConstants.MAX_DURATION),
      intThat(notInRange),
      eq("duration")
    );

    assertThrows(
      // Then
      BadInputException.class,
      // When
      () -> presentationValidator.validateRequest(presentationDto)
    );

    verify(commonValidator).requireLengthInRange(
      PresentationValidatorConstants.MIN_TITLE_LENGTH,
      PresentationValidatorConstants.MAX_TITLE_LENGTH,
      title,
      "title"
    );
    verify(commonValidator).requireNotNull(beginsAt, "beginsAt");
    verify(commonValidator).requireInRangeClosed(
      PresentationValidatorConstants.MIN_DURATION,
      PresentationValidatorConstants.MAX_DURATION,
      duration,
      "duration"
    );
    verifyNoMoreInteractions(commonValidator);
  }

  @Test
  void given_valid_presentation_should_do_nothing_when_validateRequest() {
    // Given
    final var title = "presentation";
    final var beginsAt = LocalDateTime.now();
    final var duration = PresentationValidatorConstants.MIN_DURATION;
    final var presentationDto = PresentationDto.builder()
      .title(title)
      .beginsAt(beginsAt)
      .duration(duration)
      .build();

    doNothing().when(commonValidator).requireLengthInRange(
      PresentationValidatorConstants.MIN_TITLE_LENGTH,
      PresentationValidatorConstants.MAX_TITLE_LENGTH,
      title,
      "title"
    );
    doNothing().when(commonValidator).requireNotNull(beginsAt, "beginsAt");
    doNothing().when(commonValidator).requireInRangeClosed(
      PresentationValidatorConstants.MIN_DURATION,
      PresentationValidatorConstants.MAX_DURATION,
      duration,
      "duration"
    );

    // When
    presentationValidator.validateRequest(presentationDto);

    // Then
    verify(commonValidator).requireLengthInRange(
      PresentationValidatorConstants.MIN_TITLE_LENGTH,
      PresentationValidatorConstants.MAX_TITLE_LENGTH,
      title,
      "title"
    );
    verify(commonValidator).requireNotNull(beginsAt, "beginsAt");
    verify(commonValidator).requireInRangeClosed(
      PresentationValidatorConstants.MIN_DURATION,
      PresentationValidatorConstants.MAX_DURATION,
      duration,
      "duration"
    );
    verifyNoMoreInteractions(commonValidator);
  }

}
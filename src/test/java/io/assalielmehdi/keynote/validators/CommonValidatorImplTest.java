package io.assalielmehdi.keynote.validators;

import io.assalielmehdi.keynote.exceptions.BadInputException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class CommonValidatorImplTest {

  @InjectMocks
  private CommonValidatorImpl commonValidator;

  @Test
  void given_null_object_should_throw_bad_input_exception_when_requireNotNull() {
    // Given
    final var name = "field";

    final var exception = assertThrows(
      // Then
      BadInputException.class,
      // When
      () -> commonValidator.requireNotNull(null, name)
    );

    assertThat(exception.getMessage()).contains(name);
  }

  @Test
  void given_not_null_object_should_do_nothing_when_requireNotNull() {
    // Given

    // Then
    assertDoesNotThrow(
      // When
      () -> commonValidator.requireNotNull(new Object(), "field")
    );
  }

  @ParameterizedTest
  @ValueSource(ints = {-1, 11})
  void given_not_in_range_value_should_throw_bad_input_exception_when_requireInRangeClosed(final int number) {
    // Given
    final var name = "field";

    final var exception = assertThrows(
      // Then
      BadInputException.class,
      // When
      () -> commonValidator.requireInRangeClosed(
        0,
        10,
        number,
        name
      )
    );

    assertThat(exception.getMessage()).contains(name);
  }

  @Test
  void given_in_range_value_should_do_nothing_when_requireInRangeClosed() {
    // Given

    // Then
    assertDoesNotThrow(
      // When
      () -> commonValidator.requireInRangeClosed(
        0,
        10,
        5,
        "field"
      )
    );
  }

  @Test
  void given_null_string_should_throw_bad_input_exception_when_requireNotBlank() {
    // Given
    final var name = "field";

    final var exception = assertThrows(
      // Then
      BadInputException.class,
      // When
      () -> commonValidator.requireNotBlank(null, name)
    );

    assertThat(exception.getMessage()).contains(name);
  }

  @ParameterizedTest
  @ValueSource(strings = {"", "  ", "     "})
  void given_blank_string_should_throw_bad_input_exception_when_requireNotBlank(final String value) {
    // Given
    final var name = "field";

    final var exception = assertThrows(
      // Then
      BadInputException.class,
      // When
      () -> commonValidator.requireNotBlank(value, name)
    );

    assertThat(exception.getMessage()).contains(name);
  }

  @Test
  void given_not_blank_string_should_do_nothing_when_requireNotBlank() {
    // Given

    // Then
    assertDoesNotThrow(
      // When
      () -> commonValidator.requireNotBlank("value", "field")
    );
  }

  @Test
  void given_null_string_should_throw_bad_input_exception_when_requireLengthInRange() {
    // Given
    final var name = "field";

    final var exception = assertThrows(
      // Then
      BadInputException.class,
      // When
      () -> commonValidator.requireLengthInRange(0, 0, null, name)
    );

    assertThat(exception.getMessage()).contains(name);
  }

  @Test
  void given_too_short_string_should_throw_bad_input_exception_when_requireLengthInRange() {
    // Given
    final var name = "field";

    final var exception = assertThrows(
      // Then
      BadInputException.class,
      // When
      () -> commonValidator.requireLengthInRange(
        5,
        10,
        "val",
        name
      )
    );

    assertThat(exception.getMessage()).contains(name);
  }

  @Test
  void given_too_long_string_should_throw_bad_input_exception_when_requireLengthInRange() {
    // Given
    final var name = "field";

    final var exception = assertThrows(
      // Then
      BadInputException.class,
      // When
      () -> commonValidator.requireLengthInRange(
        2,
        4,
        "value",
        name
      )
    );

    assertThat(exception.getMessage()).contains(name);
  }

}
package io.assalielmehdi.keynote.helpers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class StringHelperImplTest {

  @InjectMocks
  private StringHelperImpl stringHelper;

  @Test
  void given_null_value_should_return_null_when_removeExtraBlank() {
    // Given

    // When
    final var withoutExtraBlank = stringHelper.removeExtraBlank(null);

    // Then
    assertThat(withoutExtraBlank).isNull();
  }

  @Test
  void given_not_null_value_should_remove_extra_blank_when_remove_extraBlank() {
    // Given
    final var value = "  This  is     full of   extra     blank    ";
    final var expectedWithoutExtraBlank = "This is full of extra blank";

    // When
    final var withoutExtraBlank = stringHelper.removeExtraBlank(value);

    // Then
    assertThat(withoutExtraBlank).isEqualTo(expectedWithoutExtraBlank);
  }

}
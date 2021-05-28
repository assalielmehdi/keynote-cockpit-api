package io.assalielmehdi.keynote.helpers;

import io.assalielmehdi.keynote.dto.PresentationDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PresentationHelperImplTest {

  @Mock
  private StringHelper stringHelper;

  @InjectMocks
  private PresentationHelperImpl presentationHelper;

  @Test
  void given_dto_with_extra_blank_title_should_remove_extra_blank_when_normalizeRequest() {
    // Given
    final var titleWithExtraBlank = "   Test     blank   title  ";
    final var titleWithoutExtraBlank = "Test blank title";
    final var dto = PresentationDto.builder()
      .title(titleWithExtraBlank)
      .build();

    when(stringHelper.removeExtraBlank(titleWithExtraBlank)).thenReturn(titleWithoutExtraBlank);

    // When
    presentationHelper.normalizeRequest(dto);

    // Then
    verify(stringHelper).removeExtraBlank(titleWithExtraBlank);
    verifyNoMoreInteractions(stringHelper);
    assertThat(dto.getTitle()).isEqualTo(titleWithoutExtraBlank);
  }

  @Test
  void given_not_null_token_should_set_token_to_null_when_normalizeRequest() {
    // Given
    final var title = "presentation";
    final var dto = PresentationDto.builder()
      .title(title)
      .token("token")
      .build();

    when(stringHelper.removeExtraBlank(title)).thenReturn(title);

    // When
    presentationHelper.normalizeRequest(dto);

    // Then
    verify(stringHelper).removeExtraBlank(title);
    verifyNoMoreInteractions(stringHelper);
    assertThat(dto.getTitle()).isEqualTo(title);
    assertThat(dto.getToken()).isNull();
  }

}
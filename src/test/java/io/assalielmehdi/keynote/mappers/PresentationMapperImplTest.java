package io.assalielmehdi.keynote.mappers;

import io.assalielmehdi.keynote.dto.PresentationDto;
import io.assalielmehdi.keynote.exceptions.ServerErrorException;
import io.assalielmehdi.keynote.models.Presentation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class PresentationMapperImplTest {

  @InjectMocks
  private PresentationMapperImpl presentationMapper;

  @Test
  void given_null_dto_should_throw_server_error_exception_when_fromDto() {
    // Given

    assertThrows(
      // Then
      ServerErrorException.class,
      // When
      () -> presentationMapper.fromDto(null)
    );
  }

  @Test
  void given_dto_should_map_to_model_correctly_when_fromDto() {
    // Given
    final var title = "presentation";
    final var beginsAt = LocalDateTime.now();
    final var duration = 60;
    final var token = "token";
    final var dto = PresentationDto.builder()
      .title(title)
      .beginsAt(beginsAt)
      .duration(duration)
      .token(token)
      .build();

    // When
    var model = presentationMapper.fromDto(dto);

    // Then
    assertThat(model.getId()).isNull();
    assertThat(model.getOwner()).isNull();
    assertThat(model.getTitle()).isEqualTo(title);
    assertThat(model.getBeginsAt()).isEqualTo(beginsAt);
    assertThat(model.getDuration()).isEqualTo(duration);
    assertThat(model.getToken()).isEqualTo(token);
  }

  @Test
  void given_null_model_should_throw_server_error_exception_when_toDto() {
    // Given

    assertThrows(
      // Then
      ServerErrorException.class,
      // When
      () -> presentationMapper.toDto(null)
    );
  }

  @Test
  void given_model_should_map_to_dto_correctly_when_toDto() {
    // Given
    final var title = "presentation";
    final var beginsAt = LocalDateTime.now();
    final var duration = 60;
    final var token = "token";
    final var model = Presentation.builder()
      .title(title)
      .beginsAt(beginsAt)
      .duration(duration)
      .token(token)
      .build();

    // When
    var dto = presentationMapper.toDto(model);

    // Then
    assertThat(dto.getTitle()).isEqualTo(title);
    assertThat(dto.getBeginsAt()).isEqualTo(beginsAt);
    assertThat(dto.getDuration()).isEqualTo(duration);
    assertThat(dto.getToken()).isEqualTo(token);
  }

}
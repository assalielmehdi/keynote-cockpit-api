package io.assalielmehdi.keynote.services;

import io.assalielmehdi.keynote.dto.PresentationDto;
import io.assalielmehdi.keynote.exceptions.BadInputException;
import io.assalielmehdi.keynote.helpers.PresentationHelper;
import io.assalielmehdi.keynote.mappers.PresentationMapper;
import io.assalielmehdi.keynote.models.Presentation;
import io.assalielmehdi.keynote.repositories.PresentationRepository;
import io.assalielmehdi.keynote.validators.PresentationValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PresentationServiceImplTest {

  @Mock
  private PresentationHelper presentationHelper;

  @Mock
  private PresentationValidator presentationValidator;

  @Mock
  private PresentationMapper presentationMapper;

  @Mock
  private PresentationRepository presentationRepository;

  @Mock
  private TokenService tokenService;

  @InjectMocks
  private PresentationServiceImpl presentationService;

  @Test
  void given_not_valid_dto_should_throw_bad_input_exception_when_create() {
    // Given
    final var dto = mock(PresentationDto.class);

    doNothing().when(presentationHelper).normalizeRequest(dto);
    doThrow(BadInputException.class).when(presentationValidator).validateRequest(dto);

    assertThrows(
      // Then
      BadInputException.class,
      // When
      () -> presentationService.create(dto)
    );

    verify(presentationHelper).normalizeRequest(dto);
    verify(presentationValidator).validateRequest(dto);
    verifyNoInteractions(tokenService);
    verifyNoInteractions(presentationRepository);
  }

  @Test
  void given_valid_dto_should_create_and_generate_token_when_create() {
    // Given
    final var title = "presentation";
    final var beginsAt = LocalDateTime.now();
    final var duration = 60;
    final var token = "token";
    final var dto = PresentationDto.builder()
      .title(title)
      .beginsAt(beginsAt)
      .duration(duration)
      .token(null)
      .build();
    final var model = mock(Presentation.class);

    doNothing().when(presentationHelper).normalizeRequest(dto);
    doNothing().when(presentationValidator).validateRequest(dto);
    when(tokenService.generate()).thenReturn(token);
    when(presentationMapper.fromDto(dto)).thenReturn(model);
    when(presentationRepository.save(model)).thenReturn(model);

    // When
    final var savedDto = presentationService.create(dto);

    // Then
    verify(presentationHelper).normalizeRequest(dto);
    verify(presentationValidator).validateRequest(dto);
    verify(tokenService).generate();
    verify(presentationMapper).fromDto(dto);
    verify(presentationRepository).save(model);
    assertThat(savedDto).isEqualTo(dto);
  }

}
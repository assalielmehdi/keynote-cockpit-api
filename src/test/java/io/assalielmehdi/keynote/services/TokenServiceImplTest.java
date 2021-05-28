package io.assalielmehdi.keynote.services;

import io.assalielmehdi.keynote.models.Presentation;
import io.assalielmehdi.keynote.repositories.PresentationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TokenServiceImplTest {

  @Mock
  private PresentationRepository presentationRepository;

  @InjectMocks
  private TokenServiceImpl tokenService;

  @Test
  void given_tokens_found_two_times_should_regenerate_token_three_times_when_generate() {
    // Given
    final var presentation = mock(Presentation.class);

    when(presentationRepository.findByToken(anyString()))
      .thenReturn(Optional.of(presentation))
      .thenReturn(Optional.of(presentation))
      .thenReturn(Optional.empty());

    // When
    tokenService.generate();

    // Then
    verify(presentationRepository, times(3)).findByToken(anyString());
  }

}
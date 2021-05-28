package io.assalielmehdi.keynote.controllers;

import io.assalielmehdi.keynote.dto.PresentationDto;
import io.assalielmehdi.keynote.services.PresentationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PresentationControllerTest {

  @Mock
  private PresentationService presentationService;

  @InjectMocks
  private PresentationController presentationController;

  @Test
  void given_presentation_dto_should_call_presentationService_create_when_create() {
    // Given
    final var dto = mock(PresentationDto.class);

    // When
    presentationController.create(dto);

    // Then
    verify(presentationService).create(dto);
  }

}
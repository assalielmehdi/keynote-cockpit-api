package io.assalielmehdi.keynote.services;

import io.assalielmehdi.keynote.dto.PresentationDto;
import io.assalielmehdi.keynote.helpers.PresentationHelper;
import io.assalielmehdi.keynote.mappers.PresentationMapper;
import io.assalielmehdi.keynote.repositories.PresentationRepository;
import io.assalielmehdi.keynote.validators.PresentationValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PresentationServiceImpl implements PresentationService {

  private final PresentationHelper presentationHelper;

  private final PresentationValidator presentationValidator;

  private final PresentationMapper presentationMapper;

  private final PresentationRepository presentationRepository;

  private final TokenService tokenService;

  @Override
  public PresentationDto create(PresentationDto presentationDto) {
    presentationHelper.normalizeRequest(presentationDto);
    presentationValidator.validateRequest(presentationDto);

    final var token = tokenService.generate();
    presentationDto.setToken(token);

    final var presentation = presentationMapper.fromDto(presentationDto);
    presentationRepository.save(presentation);

    return presentationDto;
  }

}

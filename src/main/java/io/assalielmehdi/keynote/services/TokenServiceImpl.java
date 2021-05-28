package io.assalielmehdi.keynote.services;

import io.assalielmehdi.keynote.repositories.PresentationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class TokenServiceImpl implements TokenService {

  private final PresentationRepository presentationRepository;

  @Override
  public String generate() {
    String token;

    do {
      token = UUID.randomUUID().toString();
    } while (presentationRepository.findByToken(token).isPresent());

    return token;
  }

}

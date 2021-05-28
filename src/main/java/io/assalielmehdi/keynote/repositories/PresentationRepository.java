package io.assalielmehdi.keynote.repositories;

import io.assalielmehdi.keynote.models.Presentation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface PresentationRepository extends MongoRepository<Presentation, String> {

  Optional<Presentation> findByToken(String token);

}

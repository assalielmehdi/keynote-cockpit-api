package io.assalielmehdi.keynote.repositories;

import io.assalielmehdi.keynote.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {

  Optional<User> findByEmail(String email);

}

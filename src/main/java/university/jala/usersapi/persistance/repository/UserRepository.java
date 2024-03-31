package university.jala.usersapi.persistance.repository;

import java.util.Optional;
import university.jala.usersapi.domain.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface UserRepository defines the methods that will be used to
 * interact with the database.
 */
@Repository
public interface UserRepository extends JpaRepository<User, String> {

  Optional<User> findByLogin(String login);
}

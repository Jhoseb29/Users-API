package university.jala.usersapi.data.mongodb.repository;

import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import university.jala.usersapi.core.domain.models.entities.User;

/**
 * The interface UserRepository defines the methods that
 * will be used to interact with the MongoDB database.
 */
@Repository
public interface UserRepository extends MongoRepository<User, String> {

    /**
     * Customized Query Method for getting a user by its login.
     *
     * @param login login.
     * @return user matching the login.
     */
    Optional<User> findByLogin(String login);
}

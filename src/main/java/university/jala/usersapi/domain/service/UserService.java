package university.jala.usersapi.domain.service;

import java.util.Optional;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import university.jala.usersapi.domain.models.User;
import university.jala.usersapi.persistance.repository.UserRepository;

/**
 * This class contains the logic to perform CRUD operations
 * related to users. It is used by the controller to handle HTTP
 * requests related to users. It uses a UserRepository to handle CRUD
 * Operations on the db.
 */
@Service
@Setter
public class UserService {

  /**
   * UserRepository Instance.
   **/
  @Autowired
  private UserRepository userRepository;

  /**
   * Method get user by Id.
   *
   * @param userId
   * @return user.
   */
  public Optional<User> getUserById(final String userId) {
    return userRepository.findById(userId);
  }


  /**
   * @param id User ID
   * @return Returns Boolean if the User is deleted.
   */
  public Optional<User> deleteById(final String id) {
    Optional<User> user = getUserById(id);
    if (user.isPresent()) {
      userRepository.deleteById(id);
    }
    return user;
  }
}

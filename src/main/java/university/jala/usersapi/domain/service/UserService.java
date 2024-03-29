package university.jala.usersapi.domain.service;
import org.springframework.data.domain.PageRequest;
import university.jala.usersapi.domain.models.User;
import university.jala.usersapi.persistance.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import java.util.List;


/**
 * This class contains the logic to perform CRUD operations
 * related to users. It is used by the controller to handle HTTP
 * requests related to users. It uses a UserRepository to handle CRUD
 * Operations on the db.
 */
@Service
public class UserService {

  /**
   * UserRepository Instance.
   **/
  @Autowired
  private UserRepository userRepository;

  /**
   * @return return getAllUsers
   * @param page The page number
   * @param size The size of the page
   */
  public List<User> getAllUsers(final int page, final int size) {
    return userRepository.findAll(PageRequest.of(page, size)).getContent();
  }

    /**
   * Method get user by Id.
   *
   * @param userId
   * @return user.
   */
  public Optional<User> getUserById(final String userId) {
    return userRepository.findById(userId);
  }

}


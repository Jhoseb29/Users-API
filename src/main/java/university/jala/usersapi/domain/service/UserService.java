package university.jala.usersapi.domain.service;

import java.util.Optional;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import university.jala.usersapi.domain.models.User;
import university.jala.usersapi.persistance.repository.UserRepository;




import java.util.List;



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
   * UserValidationService Instance.
   **/
  @Autowired
  private UserValidationService userValidationService;

  /**
   * @param page The page number
   * @param size The size of the page
   * @return List of users for the given page and size
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

  /**
   * Updates a user identified by ID with the provided user data.
   *
   * @param request The updated user data
   * @param id      The ID of the user to be updated
   * @return The updated user if found, otherwise null
   */
  public User updateByID(final User request, final String id) {
    Optional<User> optionalUser = userRepository.findById(id);

    if (optionalUser.isPresent()) {
      User existingUser = optionalUser.get();
      User validatedUser = userValidationService.validateFieldsToUpdate(
              existingUser, request);
      return userRepository.save(validatedUser);
    }
    return null;
  }

  /**
   * @param id User ID
   * @return Returns User if the User is deleted.
   */
  public Optional<User> deleteById(final String id) {
    Optional<User> user = getUserById(id);
    if (user.isPresent()) {
      userRepository.deleteById(id);
    }
    return user;
  }
}

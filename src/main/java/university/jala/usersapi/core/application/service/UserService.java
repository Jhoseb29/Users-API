package university.jala.usersapi.core.application.service;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import university.jala.usersapi.core.domain.exceptions.UserNotFoundException;
import university.jala.usersapi.core.domain.abstractions.UserDataService;
import university.jala.usersapi.core.domain.models.dto.response.UserGetAllResponseDTO;
import university.jala.usersapi.core.domain.models.mappers.UserMapper;
import university.jala.usersapi.core.domain.models.entities.User;
import university.jala.usersapi.core.domain.models.dto.response.UserDTOById;
import university.jala.usersapi.core.application.utils.PasswordEncrypterUtil;
import university.jala.usersapi.core.application.utils.UpdatableValidationUtil;
import university.jala.usersapi.data.mongodb.repository.UserRepository;


import java.util.List;
import java.util.stream.Collectors;

/**
 * This class contains the logic to perform CRUD operations related to users.
 * It is used by the controller to handle HTTP requests related to users.
 * It uses a UserRepository to handle CRUD Operations on the db.
 */
@Component
@Setter
@RequiredArgsConstructor
public class UserService implements UserDataService {

  /**
   * UserRepository Instance.
   **/
  @Autowired
  private UserRepository userRepository;

  /**
   * UpdatableValidationUtil Instance.
   **/
  @Autowired
  private UpdatableValidationUtil updatableValidationUtil;

  /**
   * PasswordEncrypterUtil.
   */
  private final PasswordEncrypterUtil passwordEncrypterUtil;

  /**
   * @param page The page number
   * @param size The size of the page
   * @return List of users for the given page and size
   */
  public List<UserGetAllResponseDTO> getAllUsersDTO(
      final int page, final int size) {
    List<User> users = userRepository.findAll(PageRequest.of(page, size))
        .getContent();
    return users.stream()
        .map(UserMapper::convertToDTO)
        .collect(Collectors.toList());
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
   * @return The updated user DTO if found, otherwise null
   */
  public User updateByID(final UserDTOById request, final String id)
      throws Exception {

    Optional<User> optionalUser = userRepository.findById(id);

    if (optionalUser.isPresent()) {

      User existingUser = optionalUser.get();

      updatableValidationUtil.validateFieldsToUpdate(existingUser, request);

      if (request.getPassword() != null) {
        String newUserHashedPassword = passwordEncrypterUtil.encryptPassword(
            request.getPassword());
        existingUser.setPassword(newUserHashedPassword);
      }

      userRepository.save(existingUser);
      return existingUser;
    }
    throw new UserNotFoundException(
        "The user with the ID: " + id + " was not found.");
  }

  /**
   * @param id User ID
   * @return Returns User if the User is deleted.
   */
  public User deleteById(final String id) {
    Optional<User> user = getUserById(id);
    if (user.isPresent()) {
      userRepository.deleteById(id);
      return user.get();
    }
    return null;

  }
}

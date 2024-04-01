package university.jala.usersapi.domain.service;

import org.springframework.stereotype.Service;
import university.jala.usersapi.domain.models.User;

/**
 * Service class responsible for validating user fields when updating a user.
 */
@Service
public class UserValidationService {

  /**
   * Updates the fields of an existing user with the data from an updated
   * user.
   *
   * @param existingUser The existing user
   * @param updatedUser  The updated user data
   */
  public void validateFieldsToUpdate(final User existingUser,
      final User updatedUser) {
    if (updatedUser.getName() != null) {
      existingUser.setName(updatedUser.getName());
    }
    if (updatedUser.getLogin() != null) {
      existingUser.setLogin(updatedUser.getLogin());
    }
    if (updatedUser.getPassword() != null) {
      existingUser.setPassword(updatedUser.getPassword());
    }
  }
}

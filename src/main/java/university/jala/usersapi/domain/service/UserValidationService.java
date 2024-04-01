package university.jala.usersapi.domain.service;

import org.springframework.stereotype.Service;
import university.jala.usersapi.domain.models.User;

/**
 * Service class responsible for validating user fields when updating a user.
 */
@Service
public class UserValidationService {

  /**
   * Validates the fields to update in a user object. If a field in the
   * updatedUser is not null, it updates the corresponding field in the
   * validatedUser. If a field in the updatedUser is null, it retains the value
   * from the existingUser.
   *
   * @param existingUser The existing user object with current values.
   * @param updatedUser  The updated user object with new values.
   * @return A user object with validated fields based on the update operation.
   */
  public User validateFieldsToUpdate(final User existingUser,
      final User updatedUser) {
    User validatedUser = new User();
    if (updatedUser.getName() != null) {
      validatedUser.setName(updatedUser.getName());
    } else {
      validatedUser.setName(existingUser.getName());
    }
    if (updatedUser.getLogin() != null) {
      validatedUser.setLogin(updatedUser.getLogin());
    } else {
      validatedUser.setLogin(existingUser.getLogin());
    }
    if (updatedUser.getPassword() != null) {
      validatedUser.setPassword(updatedUser.getPassword());
    } else {
      validatedUser.setPassword(existingUser.getPassword());
    }
    return validatedUser;
  }
}

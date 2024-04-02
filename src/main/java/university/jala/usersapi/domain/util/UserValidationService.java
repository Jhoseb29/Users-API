package university.jala.usersapi.domain.util;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import university.jala.usersapi.domain.models.User;
import university.jala.usersapi.domain.models.dto.UserDTOById;

/**
 * UserDataService class responsible for
 * validating user fields when updating a user.
 */
@Service
@RequiredArgsConstructor
public class UserValidationService {

  /**
   * The password encoder.
   */
  private final PasswordEncoder passwordEncoder;

  /**
   * Updates the fields of an existing user with the data from an updated
   * user.
   *
   * @param existingUser The existing user
   * @param request      The updated user data
   */
  public void validateFieldsToUpdate(final User existingUser,
      final UserDTOById request) {
    if (request.getName() != null) {
      existingUser.setName(request.getName());
    }
    if (request.getLogin() != null) {
      existingUser.setLogin(request.getLogin());
    }
    if (request.getPassword() != null && !passwordEncoder.matches(
        request.getPassword(), existingUser.getPassword())) {
      existingUser.setPassword(passwordEncoder.encode(request.getPassword()));
    }
  }
}

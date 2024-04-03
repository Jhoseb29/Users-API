package university.jala.usersapi.domain.util;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import university.jala.usersapi.domain.models.User;
import university.jala.usersapi.domain.models.dto.UserDTOById;
import university.jala.usersapi.domain.service.exception.WrongDataException;

/**
 * UserDataService class responsible for validating
 * user fields when updating a user.
 */
@Service
@RequiredArgsConstructor
public class UpdatableValidationUtil {

  /**
   * DataValidator instance to validate data.
   */
  private final DataValidator dataValidator;

  /**
   * Updates the fields of an existing user with the data from an updated user.
   *
   * @param existingUser The existing user
   * @param request      The updated user data
   */
  public void validateFieldsToUpdate(final User existingUser,
      final UserDTOById request) throws WrongDataException {

    if (request.getName() != null) {
      existingUser.setName(request.getName());
      dataValidator.validate(UserFields.USER_NAME_FIELD.getField(),
          request.getName());
    }
    if (request.getLogin() != null) {
      existingUser.setLogin(request.getLogin());
      dataValidator.validate(UserFields.USER_LOGIN_FIELD.getField(),
          request.getLogin());
    }
    if (request.getPassword() != null) {
      existingUser.setPassword(request.getPassword());
      dataValidator.validate(UserFields.USER_PASSWORD_FIELD.getField(),
          request.getPassword());
    }
  }
}

package university.jala.usersapi.core.application.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import university.jala.usersapi.core.domain.exceptions.WrongDataException;
import university.jala.usersapi.core.domain.models.entities.User;
import university.jala.usersapi.core.domain.models.dto.response.UserDTOById;

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

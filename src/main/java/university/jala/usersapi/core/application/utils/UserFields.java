package university.jala.usersapi.core.application.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Enum to hold user field names as constants.
 */
@AllArgsConstructor
@Getter
public enum UserFields {
  /**
   * Constant representing the username field.
   */
  USER_NAME_FIELD("name"),

  /**
   * Constant representing the user login field.
   */
  USER_LOGIN_FIELD("login"),

  /**
   * Constant representing the user password field.
   */
  USER_PASSWORD_FIELD("password");

  /**
   * Field name for the user field constant.
   */
  private final String field;
}

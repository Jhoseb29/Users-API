package university.jala.usersapi.core.application.utils;

import university.jala.usersapi.core.domain.exceptions.WrongDataException;

/**
 * Data Validator Abstraction.
 */
public interface DataValidator {

  /**
   * Method that all concrete implementations must have.
   *
   * @param field field name to validate.
   * @param content content to validate.
   * @throws WrongDataException WrongDataException.
   */
  void validate(String field, String content) throws WrongDataException;
}

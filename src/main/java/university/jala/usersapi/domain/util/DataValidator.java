package university.jala.usersapi.domain.util;

import university.jala.usersapi.domain.service.exception.WrongDataException;

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

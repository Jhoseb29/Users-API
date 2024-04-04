package university.jala.usersapi.domain.service.exception;

/**
 * Exception thrown when a user already exists.
 */
public class AlreadyExistingUserException extends Exception {

  /**
   * Constructs a new UserNotFoundException with the specified detail message.
   *
   * @param message the detail message
   */
  public AlreadyExistingUserException(final String message) {
    super(message);
  }
}

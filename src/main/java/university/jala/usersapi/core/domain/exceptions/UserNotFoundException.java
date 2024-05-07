package university.jala.usersapi.core.domain.exceptions;

/**
 * Exception thrown when a user is not found.
 */
public class UserNotFoundException extends Exception {

  /**
   * Constructs a new UserNotFoundException with the specified detail message.
   *
   * @param message the detail message
   */
  public UserNotFoundException(final String message) {
    super(message);
  }
}


package university.jala.usersapi.domain.service.exception;

/**
 * Exception thrown when credentials are wrong.
 */
public class WrongCredentialsException extends Exception {

  /**
   * Constructs a new WrongCredentialsException with specified detail message.
   *
   * @param message the detail message
   */
  public WrongCredentialsException(final String message) {
    super(message);
  }
}

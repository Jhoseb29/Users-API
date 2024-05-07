package university.jala.usersapi.core.domain.exceptions;

/**
 * Exception thrown when wrong data is encountered.
 */
public class WrongDataException extends Exception {

  /**
   * Constructs a new WrongDataException with the specified detail message.
   *
   * @param message the detail message
   */
  public WrongDataException(final String message) {
    super(message);
  }
}

package university.jala.usersapi.domain.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data transfer object representing a request for user registration.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequestDTO {

  /**
   * The name of the user.
   */
  private String name;

  /**
   * The login username for the user.
   */
  private String login;

  /**
   * The password for the user.
   */
  private String password;
}

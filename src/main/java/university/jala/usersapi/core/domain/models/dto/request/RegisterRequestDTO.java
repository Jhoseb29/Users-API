package university.jala.usersapi.core.domain.models.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Data transfer object representing a request for user registration.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequestDTO extends RequestDTO {

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

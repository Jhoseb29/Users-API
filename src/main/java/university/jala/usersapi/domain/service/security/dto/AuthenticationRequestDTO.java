package university.jala.usersapi.domain.service.security.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data transfer object representing a request for user authentication.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public final class AuthenticationRequestDTO {

  /**
   * The login username for authentication.
   */
  private String login;

  /**
   * The password for authentication.
   */
  private String password;
}

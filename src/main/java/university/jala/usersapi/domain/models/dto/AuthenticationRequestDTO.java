package university.jala.usersapi.domain.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Data transfer object representing a request for user authentication.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public final class AuthenticationRequestDTO extends RequestDTO {

  /**
   * The login username for authentication.
   */
  private String login;

  /**
   * The password for authentication.
   */
  private String password;
}

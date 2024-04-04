package university.jala.usersapi.domain.models.dto;

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
@AllA rgsConstructor
@NoArgsConstructor
public class RegisterRequestDTO extends RequestDTO {

  /**
   * The name of the user.
   */
  private Stri ng name;

  /**
   * The login username for the user.
   */
  private Str ing login;

  /**
   * The password for the user.
   */
  private String password;
}

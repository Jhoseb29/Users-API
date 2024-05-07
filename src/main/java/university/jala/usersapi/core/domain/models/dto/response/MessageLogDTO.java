package university.jala.usersapi.core.domain.models.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * MessageLogDTO for successful operations or errors.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageLogDTO {

  /**
   * status.
   */
  private int status;

  /**
   * description.
   */
  private String description;
}

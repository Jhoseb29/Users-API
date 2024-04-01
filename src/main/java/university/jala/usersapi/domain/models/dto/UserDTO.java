package university.jala.usersapi.domain.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    /**
     * The unique ID for the user.
     */
    private String id;
    /**
     * The name for the user.
     */
    private String name;
    /**
     * The username for the user.
     */
    private String login;
}

package university.jala.usersapi.core.domain.models.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTOById {
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
    /**
     * The password for the user.
     */
    private String password;
}

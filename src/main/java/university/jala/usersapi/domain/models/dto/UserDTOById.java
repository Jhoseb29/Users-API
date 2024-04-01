package university.jala.usersapi.domain.models.dto;

import lombok.Data;

@Data
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

package university.jala.usersapi.domain.models;

import lombok.Data;

@Data
public final class AuthenticationRequestDTO {
 private String login;
 private String password;
}

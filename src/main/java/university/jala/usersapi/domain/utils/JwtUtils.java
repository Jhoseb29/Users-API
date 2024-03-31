package university.jala.usersapi.domain.utils;

import com.auth0.jwt.JWT;
import java.util.Date;
import org.springframework.stereotype.Component;
import com.auth0.jwt.algorithms.Algorithm;

@Component
public final class JwtUtils {

  private static final String APP_NAME = "UsersAPI";
  private static final Algorithm JWT_ALGORITHM = Algorithm.HMAC256("UsersAP1-$ecretKey");

  public String createToken(String login) {
    return JWT.create().withSubject(login).withIssuer(APP_NAME).withIssuedAt(new Date())
        .sign(JWT_ALGORITHM);
  }
}

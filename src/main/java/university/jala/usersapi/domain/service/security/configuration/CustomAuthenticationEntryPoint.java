package university.jala.usersapi.domain.service.security.configuration;

import io.jsonwebtoken.io.IOException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

  @Override
  public void commence(HttpServletRequest request,
      HttpServletResponse response,
      AuthenticationException authException) throws IOException, java.io.IOException {

    String message
        = "You're not authenticated. "
        + "You must first authenticate yourself to access this resource.";

    response.setStatus(HttpStatus.UNAUTHORIZED.value());
    response.getWriter().write(message);
  }
}

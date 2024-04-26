package university.jala.usersapi.core.application.security.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.io.IOException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import university.jala.usersapi.core.domain.models.dto.response.MessageLogDTO;

/**
 * Custom authentication entry point to handle unauthorized access.
 */
public final class CustomAuthenticationEntryPoint
    implements AuthenticationEntryPoint {

  /**
   * Invoked when an unauthenticated user tries to access a secured resource.
   *
   * @param request       the request being handled
   * @param response      the response to be sent back to the client
   * @param authException the exceptions that caused the unauthorized access
   * @throws IOException if an I/O error occurs while writing the response
   */
  @Override
  public void commence(final HttpServletRequest request,
      final HttpServletResponse response,
      final AuthenticationException authException)
      throws IOException, java.io.IOException {
    MessageLogDTO messageLogDTO
        = new MessageLogDTO(HttpStatus.UNAUTHORIZED.value(),
        "You're not authenticated. "
            + "You must first authenticate yourself to access this resource.");

    response.setStatus(HttpStatus.UNAUTHORIZED.value());
    response.setContentType("application/json");

    ObjectMapper objectMapper = new ObjectMapper();
    String jsonMessage = objectMapper.writeValueAsString(messageLogDTO);

    try (PrintWriter out = response.getWriter()) {
      out.print(jsonMessage);
      out.flush();
    }
  }
}

package university.jala.usersapi.domain.service.security.jwt;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import university.jala.usersapi.domain.service.JwtService;

/**
 * Filter responsible for JWT token-based authentication.
 */
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  /**
   * UserDataService for handling JWT tokens.
   */
  private final JwtService jwtService;

  /**
   * UserDataService for loading user details.
   */
  private final UserDetailsService userDetailsService;

  /**
   * Constant Index Beginning.
   */
  private static final int BEGIN_INDEX = 7;

  /**
   * Performs the filtering logic for JWT token-based authentication.
   *
   * @param request     The HTTP servlet request.
   * @param response    The HTTP servlet response.
   * @param filterChain The filter chain for the request.
   * @throws ServletException If a servlet exception occurs.
   * @throws IOException      If an I/O exception occurs.
   */
  @Override
  protected void doFilterInternal(final HttpServletRequest request,
      final HttpServletResponse response,
      final FilterChain filterChain) throws ServletException, IOException {

    final String token = getTokenFromRequest(request);

    if (token != null) {
      handleAuthentication(request, token);
    }

    filterChain.doFilter(request, response);
  }

  /**
   * Handles authentication based on the provided JWT token.
   *
   * @param request The HTTP servlet request.
   * @param token   The JWT token extracted from the request.
   */
  private void handleAuthentication(final HttpServletRequest request,
      final String token) {
    String login = jwtService.getLoginFromToken(token);

    if (login != null && SecurityContextHolder
        .getContext().getAuthentication() == null) {
      UserDetails userDetails = userDetailsService
          .loadUserByUsername(login);

      if (jwtService.isTokenValid(token, userDetails)) {
        UsernamePasswordAuthenticationToken authenticationToken
            = new UsernamePasswordAuthenticationToken(
            userDetails, null, userDetails.getAuthorities());

        authenticationToken.setDetails(
            new WebAuthenticationDetailsSource().buildDetails(request));

        SecurityContextHolder.getContext()
            .setAuthentication(authenticationToken);
      }
    }
  }

  /**
   * Retrieves the JWT token from the request header.
   *
   * @param request The HTTP servlet request.
   * @return The JWT token extracted from
   * the request header, or null if not found.
   */
  private String getTokenFromRequest(final HttpServletRequest request) {
    final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

    if (StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer ")) {
      return authHeader.substring(BEGIN_INDEX);
    }

    return null;
  }
}

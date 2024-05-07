package university.jala.usersapi.core.application.security.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import university.jala.usersapi.core.application.security.jwt.JwtAuthenticationFilter;

/**
 * Configuration class for web security settings.
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

  /**
   * Filter for JWT token-based authentication.
   */
  private final JwtAuthenticationFilter jwtAuthenticationFilter;

  /**
   * Authentication provider for user authentication.
   */
  private final AuthenticationProvider authenticationProvider;

  /**
   * Configures security settings for the web application.
   *
   * @param http The HttpSecurity object to configure.
   * @return The configured SecurityFilterChain.
   * @throws Exception If an exceptions occurs while configuring security.
   */
  @Bean
  public SecurityFilterChain securityFilterChain(
      final HttpSecurity http) throws Exception {
      return http
          .csrf(AbstractHttpConfigurer::disable)
          .authorizeHttpRequests(authRequest ->
              authRequest
                  .requestMatchers("/api/v1/auth/**",
                      "/swagger-ui/**", "/v3/api-docs/**").permitAll()
                  .anyRequest().authenticated()
          )
          .sessionManagement(sessionManager ->
              sessionManager
                  .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
          .authenticationProvider(authenticationProvider)
          .addFilterBefore(jwtAuthenticationFilter,
              UsernamePasswordAuthenticationFilter.class)
          .exceptionHandling(exceptionHandling ->
              exceptionHandling
                  .authenticationEntryPoint(authenticationEntryPoint())
          )
          .build();
  }

  private AuthenticationEntryPoint authenticationEntryPoint() {
    return new CustomAuthenticationEntryPoint();
  }

}

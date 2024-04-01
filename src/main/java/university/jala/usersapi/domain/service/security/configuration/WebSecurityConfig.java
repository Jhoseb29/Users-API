package university.jala.usersapi.domain.service.security.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import university.jala.usersapi.domain.service.security.jwt.JwtAuthenticationFilter;

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
   * @throws Exception If an exception occurs while configuring security.
   */
  @Bean
  public SecurityFilterChain securityFilterChain(
      final HttpSecurity http) throws Exception {
    return http
        .csrf(AbstractHttpConfigurer::disable)
        .authorizeHttpRequests(authRequest ->
            authRequest
                .requestMatchers("/auth/**").permitAll()
                .anyRequest().authenticated()
        )
        .sessionManagement(sessionManager ->
            sessionManager
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authenticationProvider(authenticationProvider)
        .addFilterBefore(jwtAuthenticationFilter,
            UsernamePasswordAuthenticationFilter.class)
        .build();
  }

}

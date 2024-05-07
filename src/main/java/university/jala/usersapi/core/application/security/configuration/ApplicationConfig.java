package university.jala.usersapi.core.application.security.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import university.jala.usersapi.data.mongodb.repository.UserRepository;

/**
 * Configuration class for application settings.
 */
@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

  /**
   * Repository for user data access.
   */
  private final UserRepository userRepository;

  /**
   * Creates the authentication manager bean.
   *
   * @param config The authentication configuration.
   * @return The authentication manager.
   * @throws Exception If an exceptions occurs.
   */
  @Bean
  public AuthenticationManager authenticationManager(
      final AuthenticationConfiguration config)
      throws Exception {
    return config.getAuthenticationManager();
  }

  /**
   * Creates the authentication provider bean.
   *
   * @return The authentication provider.
   */
  @Bean
  public AuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider authenticationProvider
        = new DaoAuthenticationProvider();
    authenticationProvider.setUserDetailsService(userDetailService());
    authenticationProvider.setPasswordEncoder(passwordEncoder());
    return authenticationProvider;
  }

  /**
   * Creates the password encoder bean.
   *
   * @return The password encoder.
   */
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  /**
   * Creates the user details service bean.
   *
   * @return The user details service.
   */
  @Bean
  public UserDetailsService userDetailService() {
    return login -> userRepository.findByLogin(login)
        .orElseThrow(() -> new UsernameNotFoundException("User not found."));
  }
}

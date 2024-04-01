package university.jala.usersapi.domain.service;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import university.jala.usersapi.domain.models.User;
import university.jala.usersapi.domain.models.dto.AuthenticationRequestDTO;
import university.jala.usersapi.domain.models.dto.AuthenticationResponseDTO;
import university.jala.usersapi.domain.models.dto.RegisterRequestDTO;
import university.jala.usersapi.persistance.repository.UserRepository;

/**
 * Corresponding service for users sign up and sign in.
 */
@Service
@RequiredArgsConstructor
public final class AuthService {

  /**
   * Interface defining data access operations for the user entity.
   */
  private final UserRepository userRepository;

  /**
   * Service responsible for generating and validating JWT tokens.
   */
  private final JwtService jwtService;

  /**
   * Password encoder used to encode and decode passwords.
   */
  private final PasswordEncoder passwordEncoder;

  /**
   * Authentication manager used to authenticate user credentials.
   */
  private final AuthenticationManager authenticationManager;

  /**
   * Method to perform user authentication based on the provided credentials.
   *
   * @param authenticationRequest User's authentication credentials.
   * @return Authentication response object containing the generated JWT token.
   */
  public AuthenticationResponseDTO login(
      final AuthenticationRequestDTO authenticationRequest) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            authenticationRequest.getLogin(),
            authenticationRequest.getPassword()));

    UserDetails userDetails
        = userRepository.findByLogin(authenticationRequest.getLogin())
        .orElseThrow();

    String token = jwtService.getToken(userDetails);

    return AuthenticationResponseDTO.builder()
        .token(token)
        .build();
  }

  /**
   * Method to perform user registration.
   *
   * @param registerRequest New User Information.
   * @return Authentication response object containing the generated JWT token.
   */
  public AuthenticationResponseDTO register(
      final RegisterRequestDTO registerRequest) {
    User user = User.builder()
        .id(UUID.randomUUID().toString())
        .name(registerRequest.getName())
        .login(registerRequest.getLogin())
        .password(passwordEncoder.encode(registerRequest.getPassword()))
        .build();

    userRepository.save(user);

    return AuthenticationResponseDTO.builder()
        .token(jwtService.getToken(user))
        .build();

  }

}

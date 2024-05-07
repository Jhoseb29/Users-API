package university.jala.usersapi.core.application.service;

import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import university.jala.usersapi.core.application.security.jwt.JwtService;
import university.jala.usersapi.core.domain.exceptions.AlreadyExistingUserException;
import university.jala.usersapi.core.domain.exceptions.UserNotFoundException;
import university.jala.usersapi.core.domain.exceptions.WrongCredentialsException;
import university.jala.usersapi.core.domain.abstractions.AuthDataService;
import university.jala.usersapi.core.domain.models.entities.User;
import university.jala.usersapi.core.domain.models.dto.request.AuthenticationRequestDTO;
import university.jala.usersapi.core.domain.models.dto.response.AuthenticationResponseDTO;
import university.jala.usersapi.core.domain.models.dto.request.RegisterRequestDTO;
import university.jala.usersapi.core.application.utils.DataValidator;
import university.jala.usersapi.data.mongodb.repository.UserRepository;

/**
 * Corresponding service for users sign up and sign in.
 */
@Component
@RequiredArgsConstructor
public final class AuthService implements AuthDataService {

  /**
   * Interface defining data access operations for the user entity.
   */
  private final UserRepository userRepository;

  /**
   * UserDataService responsible for generating and validating JWT tokens.
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
   * DataValidator instance to validate data.
   */
  private final DataValidator dataValidator;

  /**
   * Method to perform user authentication based on the provided credentials.
   *
   * @param authenticationRequest User's authentication credentials.
   * @return Authentication response object containing the generated JWT token.
   */
  public AuthenticationResponseDTO login(
      final AuthenticationRequestDTO authenticationRequest) throws Exception {

    dataValidator.validate("login", authenticationRequest.getLogin());
    dataValidator.validate("password", authenticationRequest.getPassword());

    Optional<User> user = userRepository.findByLogin(
        authenticationRequest.getLogin());
    if (user.isEmpty()) {
      throw new UserNotFoundException("The user couldn't be found.");
    }
    User userToLogin = user.get();
    try {
      authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(
              authenticationRequest.getLogin(),
              authenticationRequest.getPassword()));
      UserDetails userDetails
          = user.get();

      String token = jwtService.getToken(userDetails, userToLogin.get_id());

      return AuthenticationResponseDTO.builder()
          .token(token)
          .build();
    } catch (Exception exception) {
      throw new WrongCredentialsException("The password is wrong.");
    }
  }

  /**
   * Method to perform user registration.
   *
   * @param registerRequest New User Information.
   * @return Authentication response object containing the generated JWT token.
   */
  public AuthenticationResponseDTO register(
      final RegisterRequestDTO registerRequest) throws Exception {

    dataValidator.validate("name", registerRequest.getName());
    dataValidator.validate("login", registerRequest.getLogin());
    dataValidator.validate("password", registerRequest.getPassword());

    Optional<User> userAlreadyExisting = userRepository.findByLogin(registerRequest.getLogin());
    if (userAlreadyExisting.isPresent()) {
      throw new AlreadyExistingUserException(
          "The field 'login' already exists in the System.");
    }

    User user = User.builder()
        ._id(UUID.randomUUID().toString())
        .name(registerRequest.getName())
        .login(registerRequest.getLogin())
        .password(passwordEncoder.encode(registerRequest.getPassword()))
        .build();

    userRepository.save(user);

    return AuthenticationResponseDTO.builder()
        .token(jwtService.getToken(user, user.get_id()))
        .build();
  }
}

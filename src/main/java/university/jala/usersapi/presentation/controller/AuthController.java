package university.jala.usersapi.presentation.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import university.jala.usersapi.domain.models.dto.AuthenticationRequestDTO;
import university.jala.usersapi.domain.models.dto.RegisterRequestDTO;
import university.jala.usersapi.domain.service.AuthDataService;
import university.jala.usersapi.domain.service.exception.UserNotFoundException;
import university.jala.usersapi.domain.service.exception.WrongDataException;

/**
 * Controller for user authentication and register.
 */
@RestController
@RequestMapping("/usersapi/v1/auth")
@RequiredArgsConstructor
public class AuthController {

  /**
   * AuthService instance to save/retrieve user.
   */
  @Autowired
  private final AuthDataService authDataService;

  /**
   * Authentication Endpoint for users. It's a public endpoint.
   *
   * @param authenticationRequest authenticationRequestDTO.
   * @return Token or error message.
   */
  @PostMapping(value = "authentication")
  public ResponseEntity<?> userAuthentication(
      @RequestBody final AuthenticationRequestDTO authenticationRequest) {
    try {
      return ResponseEntity.status(HttpStatus.OK)
          .body(authDataService.login(authenticationRequest));

    } catch (UserNotFoundException userNotFoundException) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
          .body(userNotFoundException.getMessage());

    } catch (WrongDataException wrongDataException) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body(wrongDataException.getMessage());

    } catch (Exception exception) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(exception.getMessage());
    }
  }

  /**
   * Registers a new user in the system.
   * This method receives a registration request containing user
   * information and delegates the registration process
   * to the authentication service.
   *
   * @param registerRequest The registration request containing
   *                        user information.
   * @return A ResponseEntity containing the result of the registration process.
   * If successful,
   * returns an HTTP status code 200 (OK) with the registered user.
   * If an error occurs during
   * registration, returns an HTTP status code 500 (Internal Server Error)
   * with an error message.
   */
  @PostMapping()
  public ResponseEntity<?> userRegister(
      @RequestBody final RegisterRequestDTO registerRequest) {

    try {
      return ResponseEntity.status(HttpStatus.CREATED)
          .body(authDataService.register(registerRequest));

    } catch (WrongDataException wrongDataException) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body(wrongDataException.getMessage());

    } catch (Exception exception) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body("Error: " + exception.getMessage());
    }
  }
}

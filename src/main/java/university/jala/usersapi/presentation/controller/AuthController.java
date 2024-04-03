package university.jala.usersapi.presentation.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import university.jala.usersapi.domain.models.dto.AuthenticationRequestDTO;
import university.jala.usersapi.domain.models.dto.RegisterRequestDTO;
import university.jala.usersapi.domain.service.AuthDataService;
import university.jala.usersapi.domain.service.exception.UserNotFoundException;
import university.jala.usersapi.domain.service.exception.WrongCredentialsException;
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
          .contentType(MediaType.APPLICATION_JSON)
          .body(authDataService.login(authenticationRequest));

    } catch (UserNotFoundException userNotFoundException) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
          .contentType(MediaType.APPLICATION_JSON)
          .body(userNotFoundException.getMessage());

    } catch (WrongDataException wrongDataException) {
      return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
          .contentType(MediaType.APPLICATION_JSON)
          .body(wrongDataException.getMessage());

    } catch (WrongCredentialsException wrongDataException) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .contentType(MediaType.APPLICATION_JSON)
          .body(wrongDataException.getMessage());

    } catch (Exception exception) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .contentType(MediaType.APPLICATION_JSON)
          .body(exception.getMessage());
    }
  }

  /**
   * Registers a new user in the system.
   *
   * @param registerRequest The registration request
   *                        containing user information.
   * @return A ResponseEntity containing the result of the registration process.
   */
  @PostMapping()
  public ResponseEntity<?> userRegister(
      @RequestBody final RegisterRequestDTO registerRequest) {

    try {
      return ResponseEntity.status(HttpStatus.CREATED)
          .contentType(MediaType.APPLICATION_JSON)
          .body(authDataService.register(registerRequest));

    } catch (WrongDataException wrongDataException) {
      return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
          .contentType(MediaType.APPLICATION_JSON)
          .body(wrongDataException.getMessage());

    } catch (Exception exception) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .contentType(MediaType.APPLICATION_JSON)
          .body("Error: " + exception.getMessage());
    }
  }
}

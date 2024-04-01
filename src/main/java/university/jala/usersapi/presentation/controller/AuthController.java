package university.jala.usersapi.presentation.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import university.jala.usersapi.domain.service.AuthService;
import university.jala.usersapi.domain.service.security.dto.AuthenticationRequestDTO;
import university.jala.usersapi.domain.service.security.dto.RegisterRequestDTO;

/**
 * Controller for user authentication and register.
 */
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

  /**
   * AuthService instance to save/retrieve user.
   */
  private final AuthService authService;

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
      return ResponseEntity.ok(authService.login(authenticationRequest));
    } catch (Exception exception) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body("Error: " + exception.getMessage());
    }
  }

  @PostMapping(value = "register")
  public ResponseEntity<?> userRegister(@RequestBody RegisterRequestDTO registerRequest) {
    try {
      return ResponseEntity.ok(authService.register(registerRequest));
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
              .body("Error: " + e.getMessage());
    }
  }
  }

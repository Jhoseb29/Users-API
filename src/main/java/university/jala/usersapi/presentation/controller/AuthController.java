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

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

  private final AuthService authService;

  @PostMapping(value = "authentication")
  public ResponseEntity<?> userAuthentication(@RequestBody
  AuthenticationRequestDTO authenticationRequest) {
    try {
      return ResponseEntity.ok(authService.login(authenticationRequest));
    } catch (Exception exception) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body("Error: " + exception.getMessage());
    }
  }


}

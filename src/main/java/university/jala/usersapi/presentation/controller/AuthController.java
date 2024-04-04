package university.jala.usersapi.presentation.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import university.jala.usersapi.domain.models.MessageLogDTO;
import university.jala.usersapi.domain.models.dto.AuthenticationRequestDTO;
import university.jala.usersapi.domain.models.dto.RegisterRequestDTO;
import university.jala.usersapi.domain.service.AuthDataService;
import university.jala.usersapi.domain.service.exception.AlreadyExistingUserException;
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
    List<MessageLogDTO> messageLogDTOS = new ArrayList<>();
    Map<String, Object> responseMap = new HashMap<>();

    try {
      return ResponseEntity.status(HttpStatus.OK)
          .contentType(MediaType.APPLICATION_JSON)
          .body(authDataService.login(authenticationRequest));
    } catch (UserNotFoundException userNotFoundException) {
      messageLogDTOS.add(
          new MessageLogDTO(HttpStatus.NOT_FOUND.value(),
              userNotFoundException.getMessage()));
    } catch (WrongDataException wrongDataException) {
      messageLogDTOS.add(
          new MessageLogDTO(HttpStatus.UNPROCESSABLE_ENTITY.value(),
              wrongDataException.getMessage()));
    } catch (WrongCredentialsException wrongCredentialsException) {
      messageLogDTOS.add(
          new MessageLogDTO(HttpStatus.BAD_REQUEST.value(),
              wrongCredentialsException.getMessage()));
    } catch (Exception exception) {
      messageLogDTOS.add(
          new MessageLogDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(),
              exception.getMessage()));
    }
    responseMap.put("errors", messageLogDTOS);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .contentType(MediaType.APPLICATION_JSON)
        .body(responseMap);

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
    List<MessageLogDTO> messageLogDTOS = new ArrayList<>();
    Map<String, Object> responseMap = new HashMap<>();
    try {
      return ResponseEntity.status(HttpStatus.CREATED)
          .contentType(MediaType.APPLICATION_JSON)
          .body(authDataService.register(registerRequest));
    } catch (WrongDataException wrongDataException) {
      messageLogDTOS.add(
          new MessageLogDTO(HttpStatus.UNPROCESSABLE_ENTITY.value(),
              wrongDataException.getMessage()));
    } catch (AlreadyExistingUserException alreadyExistingUserException) {
      messageLogDTOS.add(
          new MessageLogDTO(HttpStatus.CONFLICT.value(),
              alreadyExistingUserException.getMessage()));
    } catch (Exception exception) {
      messageLogDTOS.add(
          new MessageLogDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(),
              exception.getMessage()));
    }
    responseMap.put("errors", messageLogDTOS);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .contentType(MediaType.APPLICATION_JSON)
        .body(responseMap);
  }

}

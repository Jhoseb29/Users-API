package university.jala.usersapi.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import java.util.Optional;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import university.jala.usersapi.core.domain.models.dto.response.MessageLogDTO;
import university.jala.usersapi.core.domain.models.dto.response.UserGetAllResponseDTO;
import university.jala.usersapi.core.domain.models.dto.response.UserDTOById;
import university.jala.usersapi.core.domain.abstractions.UserDataService;


import java.util.List;
import university.jala.usersapi.core.domain.exceptions.UserNotFoundException;
import university.jala.usersapi.core.domain.exceptions.WrongDataException;
import org.springframework.http.MediaType;


/**
 * This class defines the endpoints related to user operations.
 * The endpoints are mapped through the
 * {@link RequestMapping} ("/users") annotation.
 * Uses a UserService for data persistence in the
 * database.
 */
@RestController
@RequestMapping("/usersapi/v1/users")
@Setter
@Tag(name = "Users resource.")
public class UserController {

  /**
   * userService Instance.
   **/
  @Autowired
  private UserDataService userDataService;

  /**
   * @param page The page number (default: 0)
   * @param size The size of the page (default: 10)
   * @return return getAllUsers
   */
  @Operation(summary = "Get All Users.")
  @GetMapping()
  public ResponseEntity<?> getAllUsers(
      @RequestParam(defaultValue = "0") final int page,
      @RequestParam(defaultValue = "10") final int size) {
    Map<String, Object> responseMap = new HashMap<>();

    try {
      List<UserGetAllResponseDTO> usersDTO
          = userDataService.getAllUsersDTO(page, size);

      if (usersDTO.isEmpty()) {
        responseMap.put("errors",
            new MessageLogDTO(HttpStatus.NOT_FOUND.value(),
                "No users found"));
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .contentType(MediaType.APPLICATION_JSON)
            .body(responseMap);
      }

      responseMap.put("count", usersDTO.size());
      responseMap.put("users", usersDTO);

      return ResponseEntity
          .status(HttpStatus.OK)
          .contentType(MediaType.APPLICATION_JSON)
          .body(responseMap);

    } catch (Exception exception) {

      responseMap.put("errors",
          new MessageLogDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(),
              "Error recovering users: " + exception.getMessage()));
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .contentType(MediaType.APPLICATION_JSON)
          .body(responseMap);
    }
  }

  /**
   * Get user by _id controller.
   *
   * @param userId userId.
   * @return response (found or not found).
   */
  @Operation(summary = "Get One User by ID.")
  @GetMapping("/{userId}")
  public ResponseEntity<?> getUserById(
      @PathVariable final String userId) {
    Optional<UserDTOById> user = userDataService.getUserById(userId);
    if (user.isPresent()) {
      return ResponseEntity.status(HttpStatus.OK)
          .contentType(MediaType.APPLICATION_JSON)
          .body(user);
    } else {
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
          .contentType(MediaType.APPLICATION_JSON)
          .body(
              new MessageLogDTO(HttpStatus.NOT_FOUND.value(),
                  "User not found with ID: " + userId));
    }
  }

  /**
   * Updates a user identified by ID with the provided user data.
   *
   * @param request The updated user data
   * @param userId  The ID of the user to be updated
   * @return The updated user DTO if found, otherwise a not found response
   */
  @Operation(summary = "Update User.")
  @PutMapping(path = "/{userId}")
  public ResponseEntity<?> updateUserById(
      @RequestBody final UserDTOById request,
      @PathVariable("userId") final String userId) {
    List<MessageLogDTO> messageLogDTOS = new ArrayList<>();
    Map<String, Object> responseMap = new HashMap<>();
    try {
      UserDTOById updatedUser = this.userDataService.updateByID(request,
          userId);
      return ResponseEntity.status(HttpStatus.OK)
          .contentType(MediaType.APPLICATION_JSON)
          .body(updatedUser);

    } catch (UserNotFoundException userNotFoundException) {
      messageLogDTOS.add(
          new MessageLogDTO(HttpStatus.NOT_FOUND.value(),
              userNotFoundException.getMessage()));

    } catch (WrongDataException wrongDataException) {
      messageLogDTOS.add(
          new MessageLogDTO(HttpStatus.UNPROCESSABLE_ENTITY.value(),
              wrongDataException.getMessage()));

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
   * @param id User ID
   * @return It will return a status of
   * ok if the user is deleted and if not found it will return a
   * not found.
   */
  @Operation(summary = "Delete User.")
  @DeleteMapping(path = "/{_id}")
  public ResponseEntity<?> deleteById(@PathVariable final String id) {
    Optional<UserDTOById> userFound = userDataService.deleteById(id);
    String message;
    String formatMessage;
    if (userFound.isPresent()) {
      message = "User %s has been successfully deleted.";
      formatMessage = String.format(message, userFound.get().getName());
      return ResponseEntity.status(HttpStatus.OK)
          .contentType(MediaType.APPLICATION_JSON)
          .body(new MessageLogDTO(HttpStatus.OK.value(), formatMessage));
    } else {
      message = "User not found with ID: %s.";
      formatMessage = String.format(message, id);
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
          .contentType(MediaType.APPLICATION_JSON)
          .body(new MessageLogDTO(HttpStatus.NOT_FOUND.value(), formatMessage));
    }
  }
}

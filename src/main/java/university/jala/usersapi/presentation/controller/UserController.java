package university.jala.usersapi.presentation.controller;

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
import university.jala.usersapi.domain.models.dto.UserDTO;
import university.jala.usersapi.domain.models.dto.UserDTOById;
import university.jala.usersapi.domain.service.UserDataService;


import java.util.List;


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
  @GetMapping()
  public ResponseEntity<?> getAllUsers(
      @RequestParam(defaultValue = "0") final int page,
      @RequestParam(defaultValue = "10") final int size) {
    try {
      List<UserDTO> usersDTO = userDataService.getAllUsersDTO(page, size);
      if (usersDTO.isEmpty()) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body("No users found");
      }
      return ResponseEntity.status(HttpStatus.OK).body(usersDTO);
    } catch (Exception exception) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body("Error recovering users: " + exception.getMessage());
    }
  }

  /**
   * Get user by id controller.
   *
   * @param userId userId.
   * @return response (found or not found).
   */
  @GetMapping("/{userId}")
  public ResponseEntity<?> getUserById(@PathVariable final String userId) {
    Optional<UserDTOById> user = userDataService.getUserById(userId);
    if (user.isPresent()) {
      return ResponseEntity.status(HttpStatus.OK).body(user);
    } else {
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
          .body("User not found with ID: " + userId);
    }
  }

  /**
   * Updates a user identified by ID with the provided user data.
   *
   * @param request The updated user data
   * @param userId  The ID of the user to be updated
   * @return The updated user DTO if found, otherwise a not found response
   */
  @PutMapping(path = "/{userId}")
  public ResponseEntity<?> updateUserById(
      @RequestBody final UserDTOById request,
      @PathVariable("userId") final String userId) {
    UserDTOById updatedUser = this.userDataService.updateByID(request, userId);

    if (updatedUser != null) {
      return ResponseEntity.status(HttpStatus.OK).body(updatedUser);
    } else {
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
          .body("The user with the ID: " + userId + " was not found.");
    }
  }

  /**
   * @param id User ID
   * @return It will return a status of ok
   * if the user is deleted and if not found it will return a
   * not found.
   */
  @DeleteMapping(path = "/{id}")
  public ResponseEntity<?> deleteById(@PathVariable final String id) {
    Optional<UserDTOById> userFound = userDataService.deleteById(id);
    String message;
    String formatMessage;
    if (userFound.isPresent()) {
      message = "User %s has been successfully deleted.";
      formatMessage = String.format(message, userFound.get().getName());
      return ResponseEntity.status(HttpStatus.OK).body(formatMessage);
    } else {
      message = "User not found with ID: %s.";
      formatMessage = String.format(message, id);
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(formatMessage);
    }
  }

}

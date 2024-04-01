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
import university.jala.usersapi.domain.models.User;
import university.jala.usersapi.domain.service.UserService;


import java.util.List;



/**
 * This class defines the endpoints related to user operations.
 * The endpoints are mapped through the {@link RequestMapping}
 * ("/users") annotation. Uses a UserService for data persistence
 * in the database.
 */

@RestController
@RequestMapping("/users")
@Setter
public class UserController {

  /** userService Instance. **/
  @Autowired
  private UserService userService;

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
      List<User> users = userService.getAllUsers(page, size);
      if (users.isEmpty()) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("No se encontraron usuarios");
      }
      return ResponseEntity.status(HttpStatus.OK).body(users);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
              .body("Error al recuperar usuarios: " + e.getMessage());
    }
  }

  /**
   * Get user by id controller.
   *
   * @param userId
   * @return response (found or not found).
   */
  @GetMapping("/{userId}")
  public ResponseEntity<?> getUserById(@PathVariable final String userId) {
    Optional<User> user = userService.getUserById(userId);
    if (user.isPresent()) {
      return ResponseEntity.status(HttpStatus.OK).body(user);
    } else {
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
              .body("Usuario no encontrado con ID: " + userId);
    }
  }

  /**
   * @param request The updated user data
   * @param userId  The ID of the user to be updated
   * @return The updated user
   */
  @PutMapping(path = "/{userId}")
  public ResponseEntity<?> updateUserById(@RequestBody final User request,
      @PathVariable("userId") final String userId) {
    User updatedUser = this.userService.updateByID(request, userId);
    if (updatedUser != null) {
      return ResponseEntity.status(HttpStatus.OK).body(updatedUser);
    } else {
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
          .body("El usuario con el ID: " + userId + " no fue encontrado.");
    }
  }

  /**
   * @param id User ID
   * @return It will return a status of ok if the user is deleted
   *           and if not found it will return a not found.
   */
  @DeleteMapping(path = "/{id}")
  public ResponseEntity<?> deleteById(@PathVariable final String id) {
    Optional<User> userFound = userService.deleteById(id);
    String menssage;
    String formattMenssage;
    if (userFound.isPresent()) {
      menssage = "User %s has been successfully deleted.";
      formattMenssage = String.format(menssage, userFound.get().getName());
      return ResponseEntity.status(HttpStatus.OK).body(formattMenssage);
    } else {
      menssage = "User not found with ID: %s.";
      formattMenssage = String.format(menssage, id);
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(formattMenssage);
    }
  }

}

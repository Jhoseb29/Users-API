package university.jala.usersapi.presentation.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import university.jala.usersapi.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import university.jala.usersapi.domain.models.User;

import java.util.List;
import java.util.Optional;


/**
 * This class defines the endpoints related to user operations. The endpoints are mapped through the
 * {@link RequestMapping} ("/users") annotation. Uses a UserService for data persistence in the
 * database.
 */

@RestController
@RequestMapping("/users")
public class UserController {

  /**
   * userService Instance.
   **/
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
    } catch (Exception exception) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body("Error al recuperar usuarios: " + exception.getMessage());
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


}

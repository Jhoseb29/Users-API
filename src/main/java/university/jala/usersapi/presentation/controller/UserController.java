package university.jala.usersapi.presentation.controller;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import university.jala.usersapi.domain.models.User;
import university.jala.usersapi.domain.service.UserService;

/**
 * This class defines the endpoints related to user operations.
 * The endpoints are mapped through the {@link RequestMapping}
 * ("/users") annotation. Uses a UserService for data persistence
 * in the database.
 */

@RestController
@RequestMapping("/users")
public class UserController {

  /**
   * UserService instance.
   */
  @Autowired
  private UserService userService;

  /**
   * Get user by id controller.
   *
   * @param userId
   * @return response (found or not found).
   */
  @GetMapping("/{userId}")
  public ResponseEntity<User> getUserById(@PathVariable final String userId) {
    Optional<User> user = userService.getUserById(userId);
    if (user.isPresent()) {
      return ResponseEntity.ok(user.get());
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  /**
   * @param id User ID
   * @return Retornará un estado de ok si se borra el usuario
   *         y si no lo encuentra lanzará un no encontrado.
   */
  @DeleteMapping(path = "/delete/{id}")
  public ResponseEntity deleteById(@PathVariable("id") String id) {
    boolean success = this.userService.deleteUser(id);
    if (success) {
      return ResponseEntity.ok(success);
    } else {
      return ResponseEntity.notFound().build();
    }
  }
}

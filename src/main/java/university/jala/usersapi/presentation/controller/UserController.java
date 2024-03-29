package university.jala.usersapi.presentation.controller;

import java.util.Optional;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
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
@Setter
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
   * @return It will return a status of ok if the user is deleted
   *           and if not found it will return a not found.
   */
  @DeleteMapping(path = "/delete/{id}")
  public boolean deleteById(@PathVariable final String id) {
    return this.userService.deleteUserById(id);
  }

}

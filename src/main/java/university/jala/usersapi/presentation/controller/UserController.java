package university.jala.usersapi.presentation.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import university.jala.usersapi.domain.models.User;
import university.jala.usersapi.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * This class defines the endpoints related to user operations. The endpoints
 * are mapped through the {@link RequestMapping}("/users") annotation. Uses a
 * UserService for data persistence in the database.
 */

@RestController
@RequestMapping("/users")
public class UserController {
  /** userService Instance. **/
  @Autowired
  private UserService userService;

  /**
   * @return return getAllUsers
   */
  @GetMapping()
  public ResponseEntity<List<User>> getAllUsers() {
    try {
      List<User> users = userService.getAllUsers();
      if (users.isEmpty()) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
      }
      return ResponseEntity.ok(users);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }
}

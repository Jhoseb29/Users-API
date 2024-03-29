package university.jala.usersapi.presentation.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
   * @param page The page number (default: 0)
   * @param size The size of the page (default: 10)
   * @return return getAllUsers
   */
  @GetMapping()
  public ResponseEntity<List<User>> getAllUsers(
          @RequestParam(defaultValue = "0") final int page,
          @RequestParam(defaultValue = "10") final int size) {
    try {
      List<User> users = userService.getAllUsers(page, size);
      if (users.isEmpty()) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(users);
      }
      return ResponseEntity.ok(users);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }
}

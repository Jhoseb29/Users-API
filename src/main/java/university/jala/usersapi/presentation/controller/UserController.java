package university.jala.usersapi.presentation.controller;

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


}

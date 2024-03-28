package university.jala.usersapi.presentation.controller;

import university.jala.usersapi.persistance.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This class contains the logic to perform CRUD operations related to users. It
 * is used by the controller to handle HTTP requests related to users.
 */
@Service
public class UserController {

  /**
   * The UserRepository instance.
   */
  @Autowired
  private UserRepository userRepository;

  /**
   * Sets the UserRepository instance.
   *
   * @param repo the UserRepository instance
   */
  public void setUserRepository(final UserRepository repo) {
    this.userRepository = repo;
  }
}

package university.jala.usersapi.domain.service;

import university.jala.usersapi.persistance.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This class contains the logic to perform CRUD operations related to users. It
 * is used by the controller to handle HTTP requests related to users. It uses a
 * UserRepository to handle CRUD Operations on the db.
 */
@Service
public class UserService {

  /** UserRepository Instance. **/
  @Autowired
  private UserRepository userRepository;

}

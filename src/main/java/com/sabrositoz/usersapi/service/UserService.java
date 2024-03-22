package com.sabrositoz.usersapi.service;

import com.sabrositoz.usersapi.controller.UserController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This class defines the endpoints related to user operations. The endpoints are mapped through the
 * @RequestMapping("/users") annotation. Uses a UserRepository for data persistence in the
 * database.
 */
@RestController
@RequestMapping("/users")
public class UserService {

	@Autowired
	private UserController userController;

}

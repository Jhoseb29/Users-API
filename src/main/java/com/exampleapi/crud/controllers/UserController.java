package com.exampleapi.crud.controllers;

import com.exampleapi.crud.models.UserModel;
import com.exampleapi.crud.services.UserService;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping
	public ArrayList<UserModel> getUsers() {
		return this.userService.getUsers();
	}

	@PostMapping
	public UserModel saveUser(@RequestBody UserModel user) {
		return this.userService.saveUser(user);
	}

	@GetMapping(path = "/{id}")
	public Optional<UserModel> getUserById(@PathVariable String id) {
		return this.userService.getById(id);
	}

	@PutMapping(path = "/{id}")
	public UserModel updateUserById(@RequestBody UserModel request, @PathVariable("id") String id) {
		return this.userService.updateByID(request, id);
	}

	@DeleteMapping(path = "/{id}")
	public String deleteUserById(@PathVariable("id") String id) {
		boolean ok = this.userService.deleteUser(id);
		if (ok) {
			return "User with ID: " + id + " is deleted";
		} else {
			return "User with ID: " + id + " is not deleted";
		}
	}
}

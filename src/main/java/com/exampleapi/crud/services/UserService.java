package com.exampleapi.crud.services;

import com.exampleapi.crud.models.UserModel;
import com.exampleapi.crud.repositories.IUserRepository;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Autowired
	IUserRepository userRepository;

	public ArrayList<UserModel> getUsers() {
		return (ArrayList<UserModel>) userRepository.findAll();
	}

	public UserModel saveUser(UserModel user) {
		return userRepository.save(user);
	}

	public Optional<UserModel> getById(String id) {
		return userRepository.findById(id);
	}

	public UserModel updateByID(UserModel request, String id) {
		UserModel userModel = userRepository.findById(id).get();
		userModel.setName(request.getName());
		userModel.setLogin(request.getLogin());
		userModel.setPassword(request.getPassword());
		userRepository.save(userModel);
		return userModel;
	}

	public Boolean deleteUser(String id) {
		try {
			userRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}

package com.proyectdwes.api.proyect.services;

import java.util.List;

import com.proyectdwes.api.proyect.models.User;

public interface UserServiceI {

	List<User> getAllUsers();

	User getUserById(Long userId);

	User createUser(User user);

	User updateUser(Long userId, User updatedUser);

	void deleteUser(Long userId);

}

// UserServiceImpl.java
package com.proyectdwes.api.proyect.services;

import com.proyectdwes.api.proyect.models.User;
import com.proyectdwes.api.proyect.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserServiceI {

	@Autowired
	private UserRepository userRepository;

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public User getUserById(Long userId) {
		return userRepository.findById(userId).orElse(null);
	}

	@Override
	public User createUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public User updateUser(Long userId, User updatedUser) {
		Optional<User> existingUser = userRepository.findById(userId);
		if (existingUser.isPresent()) {
			User userToUpdate = existingUser.get();
			// Actualiza los campos relevantes
			userToUpdate.setName(updatedUser.getName());
			userToUpdate.setEmail(updatedUser.getEmail());
			userToUpdate.setPassword(updatedUser.getPassword());
			return userRepository.save(userToUpdate);
		} else {
			return null;
		}
	}

	@Override
	public void deleteUser(Long userId) {
		userRepository.deleteById(userId);
	}
}

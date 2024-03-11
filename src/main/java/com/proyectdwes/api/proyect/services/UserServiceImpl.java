// UserServiceImpl.java
package com.proyectdwes.api.proyect.services;

import com.proyectdwes.api.proyect.dto.UserResponseDTO;
import com.proyectdwes.api.proyect.models.User;
import com.proyectdwes.api.proyect.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserServiceI {

	private UserRepository userRepository;

	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;

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

	@Override
	public List<UserResponseDTO> getAllUsers() {
		return userRepository.findAll().stream().map(user -> new UserResponseDTO(user.getFirstName(),
				user.getLastName(), user.getEmail(), user.getRoles().toString())).collect(Collectors.toList());
	}

	@Override
	public UserDetailsService userDetailsService() {
		return username -> userRepository.findByEmail(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not found"));
	}

}

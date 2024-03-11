package com.proyectdwes.api.proyect.services;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.proyectdwes.api.proyect.dto.UserResponseDTO;
import com.proyectdwes.api.proyect.models.User;

public interface UserServiceI {

	List<UserResponseDTO> getAllUsers();

	User getUserById(Long userId);

	User createUser(User user);

	User updateUser(Long userId, User updatedUser);

	void deleteUser(Long userId);

	UserDetailsService userDetailsService();

}

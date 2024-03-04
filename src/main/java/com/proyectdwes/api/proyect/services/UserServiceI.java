package com.proyectdwes.api.proyect.services;

import java.util.Optional;

import com.proyectdwes.api.proyect.models.User;

public interface UserServiceI {
	User registerUser(User newUser);

	Optional<User> loginUser(String email, String password);
}

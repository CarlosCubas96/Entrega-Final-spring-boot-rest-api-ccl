package com.proyectdwes.api.proyect.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.proyectdwes.api.proyect.models.User;
import com.proyectdwes.api.proyect.services.UserServiceI;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserServiceI userService;

	@PostMapping("/register")
	public ResponseEntity<User> registerUser(@RequestBody User newUser) {
		try {
			User registeredUser = userService.registerUser(newUser);
			return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/login")
	public ResponseEntity<String> loginUser(@RequestParam String email, @RequestParam String password) {
		Optional<User> loggedInUser = userService.loginUser(email, password);
		return loggedInUser.map(user -> new ResponseEntity<>("Login successful", HttpStatus.OK))
				.orElseGet(() -> new ResponseEntity<>("Invalid credentials", HttpStatus.UNAUTHORIZED));
	}
}

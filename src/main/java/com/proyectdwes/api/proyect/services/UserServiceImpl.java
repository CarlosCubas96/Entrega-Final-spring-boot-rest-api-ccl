package com.proyectdwes.api.proyect.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyectdwes.api.proyect.models.User;
import com.proyectdwes.api.proyect.repository.UserRepository;

@Service
public class UserServiceImpl {
	
	@Autowired
	private UserRepository userRepository;

	public User registerUser(User newUser) {
		
		// Validar que el correo electrónico no esté registrado previamente
		if (userRepository.findByEmail(newUser.getEmail()).isPresent()) {
			throw new IllegalArgumentException("El correo electrónico ya está registrado");
		}

		// Implementar lógica de validación adicional si es necesario

		// Guardar el nuevo usuario en la base de datos
		return userRepository.save(newUser);
	}

	public Optional<User> loginUser(String email, String password) {
		
		// Validar las credenciales del usuario
		return userRepository.findByEmail(email).filter(user -> user.getPassword().equals(password));
	}
}

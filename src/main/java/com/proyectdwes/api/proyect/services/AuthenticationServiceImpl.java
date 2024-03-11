package com.proyectdwes.api.proyect.services;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.proyectdwes.api.proyect.dto.JwtAuthenticationResponseDTO;
import com.proyectdwes.api.proyect.dto.SignUpRequestDTO;
import com.proyectdwes.api.proyect.dto.SigninRequestDTO;
import com.proyectdwes.api.proyect.models.Role;
import com.proyectdwes.api.proyect.models.User;
import com.proyectdwes.api.proyect.repository.UserRepository;

import lombok.Builder;

@Builder
@Service
public class AuthenticationServiceImpl implements AuthenticationServiceI {

	private UserRepository userRepository;
	private PasswordEncoder passwordEncoder;
	private JwtServiceI jwtService;
	private AuthenticationManager authenticationManager;

	public AuthenticationServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder,
			JwtServiceI jwtService, AuthenticationManager authenticationManager) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.jwtService = jwtService;
		this.authenticationManager = authenticationManager;
	}

	@Override
	public JwtAuthenticationResponseDTO signup(SignUpRequestDTO request) {
		if (Boolean.TRUE.equals(userRepository.existsByEmail(request.getEmail()))) {
			throw new IllegalArgumentException("Email already in use.");
		}

		User user = new User();
		user.setFirstName(request.getFirstName());
		user.setLastName(request.getLastName());
		user.setEmail(request.getEmail());
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		user.getRoles().add(Role.ROLE_USER);
		userRepository.save(user);
		String jwt = jwtService.generateToken(user);
		return JwtAuthenticationResponseDTO.builder().token(jwt).build();
	}

	@Override
	public JwtAuthenticationResponseDTO signin(SigninRequestDTO request) {

		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		User user = userRepository.findByEmail(request.getEmail())
				.orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));
		String jwt = jwtService.generateToken(user);
		return JwtAuthenticationResponseDTO.builder().token(jwt).build();
	}
}

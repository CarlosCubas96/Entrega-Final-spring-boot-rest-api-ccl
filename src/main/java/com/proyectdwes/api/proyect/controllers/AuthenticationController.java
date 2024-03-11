package com.proyectdwes.api.proyect.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyectdwes.api.proyect.dto.JwtAuthenticationResponseDTO;
import com.proyectdwes.api.proyect.dto.SignUpRequestDTO;
import com.proyectdwes.api.proyect.dto.SigninRequestDTO;
import com.proyectdwes.api.proyect.services.AuthenticationServiceI;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Api(value = "Authentication Controller", description = "Operations related to user authentication")
public class AuthenticationController {

	AuthenticationServiceI authenticationService;

	public AuthenticationController(AuthenticationServiceI authenticationService) {
		this.authenticationService = authenticationService;
	}

	@ApiOperation(value = "User registration", response = JwtAuthenticationResponseDTO.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "User registered successfully"),
			@ApiResponse(code = 400, message = "Invalid request"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@PostMapping("/signup")
	public ResponseEntity<JwtAuthenticationResponseDTO> signup(@RequestBody SignUpRequestDTO request) {
		return ResponseEntity.ok(authenticationService.signup(request));
	}

	@ApiOperation(value = "Sign in", response = JwtAuthenticationResponseDTO.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Sign in successful"),
			@ApiResponse(code = 401, message = "Invalid credentials"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@PostMapping("/signin")
	public ResponseEntity<JwtAuthenticationResponseDTO> signin(@RequestBody SigninRequestDTO request) {
		return ResponseEntity.ok(authenticationService.signin(request));
	}
}

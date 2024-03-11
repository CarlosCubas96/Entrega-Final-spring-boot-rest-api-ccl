package com.proyectdwes.api.proyect.services;

import com.proyectdwes.api.proyect.dto.JwtAuthenticationResponseDTO;
import com.proyectdwes.api.proyect.dto.SignUpRequestDTO;
import com.proyectdwes.api.proyect.dto.SigninRequestDTO;

public interface AuthenticationServiceI {

	JwtAuthenticationResponseDTO signup(SignUpRequestDTO request);

	JwtAuthenticationResponseDTO signin(SigninRequestDTO request);
}

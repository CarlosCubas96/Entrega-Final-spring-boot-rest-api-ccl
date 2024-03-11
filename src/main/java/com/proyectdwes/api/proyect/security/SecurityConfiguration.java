package com.proyectdwes.api.proyect.security;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.proyectdwes.api.proyect.auth.JwtAuthenticationFilter;
import com.proyectdwes.api.proyect.models.Role;
import com.proyectdwes.api.proyect.services.UserServiceI;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

	private final JwtAuthenticationFilter jwtAuthenticationFilter;
	private final UserServiceI userService;

	public SecurityConfiguration(JwtAuthenticationFilter jwtAuthenticationFilter, UserServiceI userService) {
		this.jwtAuthenticationFilter = jwtAuthenticationFilter;
		this.userService = userService;
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(AbstractHttpConfigurer::disable).authorizeRequests(request -> request

				// Permitir acceso público a rutas de autenticación y Swagger
				.antMatchers("/**", "/api/v1/auth/**", "/api/v1/swagger-ui.html", "/api/v1/api-docs/**").permitAll()

				// Configuración de autorización para rutas de bicicletas
				.antMatchers(HttpMethod.GET, "/api/v1/bicycles/**")
				.hasAnyAuthority(Role.ROLE_USER.toString(), Role.ROLE_ADMIN.toString())
				.antMatchers(HttpMethod.POST, "/api/v1/bicycles/**").hasAuthority(Role.ROLE_ADMIN.toString())
				.antMatchers(HttpMethod.PUT, "/api/v1/bicycles/**").hasAuthority(Role.ROLE_ADMIN.toString())
				.antMatchers(HttpMethod.DELETE, "/api/v1/bicycles/**").hasAuthority(Role.ROLE_ADMIN.toString())

				// Configuración de autorización para rutas de usuarios
				.antMatchers("/api/v1/users/**").hasAuthority(Role.ROLE_ADMIN.toString())

				// Todas las demás rutas requieren autenticación
				.anyRequest().authenticated()).sessionManagement(manager -> manager.sessionCreationPolicy(STATELESS))
				.authenticationProvider(authenticationProvider())
				.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userService.userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}
}

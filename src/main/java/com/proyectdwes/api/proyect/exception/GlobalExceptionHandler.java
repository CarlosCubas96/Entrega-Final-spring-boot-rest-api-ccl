package com.proyectdwes.api.proyect.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.proyectdwes.api.proyect.dto.error.ErrorDetailsResponseDTO;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<ErrorDetailsResponseDTO> handleIllegalArgumentException(IllegalArgumentException ex,
			WebRequest request) {
		ErrorDetailsResponseDTO errorDetails = new ErrorDetailsResponseDTO(new Date(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(BicycleNotFoundException.class)
	public ResponseEntity<ErrorDetailsResponseDTO> handleLibroNotFoundException(BicycleNotFoundException ex,
			WebRequest request) {
		ErrorDetailsResponseDTO errorDetails = new ErrorDetailsResponseDTO(new Date(), ex.getMessage(),
				request.getDescription(false));

		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<ErrorDetailsResponseDTO> handleNoHandlerFoundException(NoHandlerFoundException ex,
			WebRequest request) {
		ErrorDetailsResponseDTO errorDetails = new ErrorDetailsResponseDTO(new Date(), "Ruta no encontrada",
				ex.getRequestURL());

		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetailsResponseDTO> handleGlobalException(Exception ex, WebRequest request) {
		ErrorDetailsResponseDTO errorDetails = new ErrorDetailsResponseDTO(new Date(), "Error interno del servidor",
				request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<ErrorDetailsResponseDTO> handleAccessDeniedException(AccessDeniedException ex,
			WebRequest request) {
		ErrorDetailsResponseDTO errorDetails = new ErrorDetailsResponseDTO(new Date(), "Acceso denegado",
				request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.FORBIDDEN);
	}

}
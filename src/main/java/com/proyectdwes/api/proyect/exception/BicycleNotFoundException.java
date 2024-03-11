package com.proyectdwes.api.proyect.exception;

public class BicycleNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public BicycleNotFoundException(String message) {
		super(message);
	}
}
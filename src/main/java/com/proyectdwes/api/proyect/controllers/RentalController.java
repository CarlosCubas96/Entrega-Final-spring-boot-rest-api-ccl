package com.proyectdwes.api.proyect.controllers;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.proyectdwes.api.proyect.models.Rental;
import com.proyectdwes.api.proyect.services.RentalServiceI;

@RestController
@RequestMapping("/api/rentals")
public class RentalController {
	@Autowired
	private RentalServiceI rentalService;

	@PostMapping("/rent")
	public ResponseEntity<Rental> rentBicycle(@RequestParam Long userId, @RequestParam Long bicycleId,
			@RequestParam LocalDateTime startTime, @RequestParam LocalDateTime endTime) {
		try {
			// Obtenemos el usuario desde la base de datos 
			
			// User user = userService.getUserById(userId);

			Rental rental = rentalService.rentBicycle(userId, bicycleId, startTime, endTime);
			return new ResponseEntity<>(rental, HttpStatus.CREATED);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/history/{userId}")
	public ResponseEntity<List<Rental>> getRentalHistory(@PathVariable Long userId) {
		
		// User user = userService.getUserById(userId);

		List<Rental> rentalHistory = rentalService.getRentalHistory(userId);
		return new ResponseEntity<>(rentalHistory, HttpStatus.OK);
	}

	@PostMapping("/return")
	public ResponseEntity<String> returnBicycle(@RequestParam Long rentalId) {
		try {
			rentalService.returnBicycle(rentalId);
			return new ResponseEntity<>("Bicycle returned successfully", HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
}

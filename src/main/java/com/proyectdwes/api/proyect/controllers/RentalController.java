package com.proyectdwes.api.proyect.controllers;

import com.proyectdwes.api.proyect.models.Rental;
import com.proyectdwes.api.proyect.services.RentalServiceI;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/rentals")
@Api(value = "RentalController", tags = "Rental Operations")
public class RentalController {

	private RentalServiceI rentalService;

	public RentalController(RentalServiceI rentalService) {
		this.rentalService = rentalService;
	}

	@PostMapping("/createRental")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	@ApiOperation(value = "Create a new rental", notes = "Create a new rental for a user and bicycle", response = Rental.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Rental created successfully"),
			@ApiResponse(code = 400, message = "Invalid input"),
			@ApiResponse(code = 403, message = "Forbidden - Access denied"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public Rental createRental(@RequestParam Long userId, @RequestParam Long bicycleId,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startTime,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endTime) {
		return rentalService.createRental(userId, bicycleId, startTime, endTime);
	}

	@GetMapping("/all")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@ApiOperation(value = "Get all rentals", notes = "Retrieve a list of all rentals", response = Rental.class, responseContainer = "List")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved the list of rentals"),
			@ApiResponse(code = 403, message = "Forbidden - Access denied"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public List<Rental> getAllRentals() {
		return rentalService.getAllRentals();
	}

	@GetMapping("/{rentalId}")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	@ApiOperation(value = "Get rental by ID", notes = "Retrieve a rental by its ID", response = Rental.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved the rental"),
			@ApiResponse(code = 403, message = "Forbidden - Access denied"),
			@ApiResponse(code = 404, message = "Rental not found"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public Rental getRentalById(@PathVariable Long rentalId) {
		return rentalService.getRentalById(rentalId);
	}

	@GetMapping("/rentalHistory/{userId}")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	@ApiOperation(value = "Get rental history for a user", notes = "Retrieve the rental history for a user", response = Rental.class, responseContainer = "List")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved the rental history"),
			@ApiResponse(code = 403, message = "Forbidden - Access denied"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public List<Rental> getRentalHistory(@PathVariable Long userId) {
		return rentalService.getRentalHistory(userId);
	}

	@PutMapping("/updateRental/{rentalId}")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	@ApiOperation(value = "Update the end time of a rental", notes = "Update the end time of a rental", response = Rental.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Rental updated successfully"),
			@ApiResponse(code = 400, message = "Invalid input"),
			@ApiResponse(code = 403, message = "Forbidden - Access denied"),
			@ApiResponse(code = 404, message = "Rental not found"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public Rental updateRental(@PathVariable Long rentalId,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endTime) {
		return rentalService.updateRental(rentalId, endTime);
	}

	@DeleteMapping("/deleteRental/{rentalId}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@ApiOperation(value = "Delete a rental by ID", notes = "Delete a rental by its ID")
	@ApiResponses(value = { @ApiResponse(code = 204, message = "Rental deleted successfully"),
			@ApiResponse(code = 403, message = "Forbidden - Access denied"),
			@ApiResponse(code = 404, message = "Rental not found"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public void deleteRental(@PathVariable Long rentalId) {
		rentalService.deleteRental(rentalId);
	}
}

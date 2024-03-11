package com.proyectdwes.api.proyect.controllers;

import com.proyectdwes.api.proyect.models.Bicycle;
import com.proyectdwes.api.proyect.services.BicycleServiceI;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/bicycles")
@Api(value = "BicycleController", tags = "Bicycle Operations")
public class BicycleController {

	private BicycleServiceI bicycleService;

	public BicycleController(BicycleServiceI bicycleService) {
		this.bicycleService = bicycleService;
	}

	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_USER')")
	@ApiOperation(value = "Get all bicycles", notes = "Retrieve a list of all bicycles", response = Bicycle.class, responseContainer = "List")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved the list of bicycles"),
			@ApiResponse(code = 403, message = "Forbidden - Access denied"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public List<Bicycle> getAllBicycles() {
		return bicycleService.getAllBicycles();
	}

	@GetMapping("/{bicycleId}")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	@ApiOperation(value = "Get bicycle by ID", notes = "Retrieve a bicycle by its ID", response = Bicycle.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved the bicycle"),
			@ApiResponse(code = 403, message = "Forbidden - Access denied"),
			@ApiResponse(code = 404, message = "Bicycle not found"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public Bicycle getBicycleById(@PathVariable Long bicycleId) {
		return bicycleService.getBicycleById(bicycleId);
	}

	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@ApiOperation(value = "Create a new bicycle", notes = "Create a new bicycle", response = Bicycle.class)
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Bicycle created successfully"),
			@ApiResponse(code = 400, message = "Invalid input"),
			@ApiResponse(code = 403, message = "Forbidden - Access denied"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public Bicycle createBicycle(@RequestBody Bicycle bicycle) {
		return bicycleService.createBicycle(bicycle);
	}

	@PutMapping("/{bicycleId}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@ApiOperation(value = "Update an existing bicycle", notes = "Update an existing bicycle", response = Bicycle.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Bicycle updated successfully"),
			@ApiResponse(code = 400, message = "Invalid input"),
			@ApiResponse(code = 403, message = "Forbidden - Access denied"),
			@ApiResponse(code = 404, message = "Bicycle not found"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public Bicycle updateBicycle(@PathVariable Long bicycleId, @RequestBody Bicycle updatedBicycle) {
		return bicycleService.updateBicycle(bicycleId, updatedBicycle);
	}

	@DeleteMapping("/{bicycleId}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@ApiOperation(value = "Delete a bicycle", notes = "Delete a bicycle by its ID")
	@ApiResponses(value = { @ApiResponse(code = 204, message = "Bicycle deleted successfully"),
			@ApiResponse(code = 403, message = "Forbidden - Access denied"),
			@ApiResponse(code = 404, message = "Bicycle not found"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public void deleteBicycle(@PathVariable Long bicycleId) {
		bicycleService.deleteBicycle(bicycleId);
	}
}

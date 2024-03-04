package com.proyectdwes.api.proyect.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.proyectdwes.api.proyect.models.Bicycle;
import com.proyectdwes.api.proyect.services.BicycleServiceI;

@RestController
@RequestMapping("/api/bicycles")
public class BicycleController {

	@Autowired
	private BicycleServiceI bicycleService;

	@GetMapping("/available")
	public ResponseEntity<List<Bicycle>> getAvailableBicycles() {
		List<Bicycle> availableBicycles = bicycleService.getAvailableBicycles();
		return new ResponseEntity<>(availableBicycles, HttpStatus.OK);
	}

	@GetMapping("/{bicycleId}")
	public ResponseEntity<Bicycle> getBicycleDetails(@PathVariable Long bicycleId) {
		Bicycle bicycleDetails = bicycleService.getBicycleDetails(bicycleId);
		return bicycleDetails != null ? new ResponseEntity<>(bicycleDetails, HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@GetMapping("/filter")
	public ResponseEntity<List<Bicycle>> filterBicycles(@RequestParam(required = false) String location,
			@RequestParam(required = false) String type, @RequestParam(defaultValue = "9999") double maxPrice,
			@RequestParam(defaultValue = "true") boolean available) {
		List<Bicycle> filteredBicycles = bicycleService.filterBicycles(location, type, maxPrice, available);
		return new ResponseEntity<>(filteredBicycles, HttpStatus.OK);
	}
}

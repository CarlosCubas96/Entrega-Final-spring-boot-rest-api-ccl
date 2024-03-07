package com.proyectdwes.api.proyect.controllers;

import com.proyectdwes.api.proyect.models.Bicycle;
import com.proyectdwes.api.proyect.services.BicycleServiceI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bicycles")
public class BicycleController {

	@Autowired
	private BicycleServiceI bicycleService;

	@GetMapping
	public List<Bicycle> getAllBicycles() {
		return bicycleService.getAllBicycles();
	}

	@GetMapping("/{bicycleId}")
	public Bicycle getBicycleById(@PathVariable Long bicycleId) {
		return bicycleService.getBicycleById(bicycleId);
	}

	@PostMapping
	public Bicycle createBicycle(@RequestBody Bicycle bicycle) {
		return bicycleService.createBicycle(bicycle);
	}

	@PutMapping("/{bicycleId}")
	public Bicycle updateBicycle(@PathVariable Long bicycleId, @RequestBody Bicycle updatedBicycle) {
		return bicycleService.updateBicycle(bicycleId, updatedBicycle);
	}

	@DeleteMapping("/{bicycleId}")
	public void deleteBicycle(@PathVariable Long bicycleId) {
		bicycleService.deleteBicycle(bicycleId);
	}
}

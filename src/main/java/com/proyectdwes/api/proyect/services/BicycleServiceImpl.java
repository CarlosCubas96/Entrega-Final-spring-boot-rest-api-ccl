// BicycleServiceImpl.java
package com.proyectdwes.api.proyect.services;

import com.proyectdwes.api.proyect.models.Bicycle;
import com.proyectdwes.api.proyect.repository.BicycleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BicycleServiceImpl implements BicycleServiceI {

	@Autowired
	private BicycleRepository bicycleRepository;

	@Override
	public List<Bicycle> getAllBicycles() {
		return bicycleRepository.findAll();
	}

	@Override
	public Bicycle getBicycleById(Long bicycleId) {
		return bicycleRepository.findById(bicycleId).orElse(null);
	}

	@Override
	public Bicycle createBicycle(Bicycle bicycle) {
		// Puedes realizar validaciones u otras lógicas antes de guardar
		return bicycleRepository.save(bicycle);
	}

	@Override
	public Bicycle updateBicycle(Long bicycleId, Bicycle updatedBicycle) {
		Optional<Bicycle> existingBicycle = bicycleRepository.findById(bicycleId);
		if (existingBicycle.isPresent()) {
			Bicycle bicycleToUpdate = existingBicycle.get();
			bicycleToUpdate.setModel(updatedBicycle.getModel());
			bicycleToUpdate.setHourlyRate(updatedBicycle.getHourlyRate());
			bicycleToUpdate.setAvailable(updatedBicycle.isAvailable());
			return bicycleRepository.save(bicycleToUpdate);
		} else {
			return null;
		}
	}

	@Override
	public void deleteBicycle(Long bicycleId) {
		bicycleRepository.deleteById(bicycleId);
	}
}

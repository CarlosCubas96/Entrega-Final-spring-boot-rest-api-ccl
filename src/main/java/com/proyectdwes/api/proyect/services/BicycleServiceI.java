package com.proyectdwes.api.proyect.services;

import java.util.List;

import com.proyectdwes.api.proyect.models.Bicycle;

public interface BicycleServiceI {

	List<Bicycle> getAllBicycles();

	Bicycle getBicycleById(Long bicycleId);

	Bicycle createBicycle(Bicycle bicycle);

	Bicycle updateBicycle(Long bicycleId, Bicycle updatedBicycle);

	void deleteBicycle(Long bicycleId);

}

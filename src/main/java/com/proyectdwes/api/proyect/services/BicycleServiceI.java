package com.proyectdwes.api.proyect.services;

import java.util.List;

import com.proyectdwes.api.proyect.models.Bicycle;

public interface BicycleServiceI {
	List<Bicycle> getAvailableBicycles();

	Bicycle getBicycleDetails(Long bicycleId);

	List<Bicycle> filterBicycles(String location, String type, double maxPrice, boolean available);
}

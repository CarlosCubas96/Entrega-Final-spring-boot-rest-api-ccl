package com.proyectdwes.api.proyect.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyectdwes.api.proyect.models.Bicycle;
import com.proyectdwes.api.proyect.repository.BicycleRepository;

@Service
public class BicycleServiceImpl {
	@Autowired
	private BicycleRepository bicycleRepository;

	public List<Bicycle> getAvailableBicycles() {
		// Obtener todas las bicicletas disponibles
		return bicycleRepository.findByAvailableTrue();
	}

	public Bicycle getBicycleDetails(Long bicycleId) {
		// Obtener los detalles de una bicicleta específica
		return bicycleRepository.findById(bicycleId).orElse(null);
	}

	public List<Bicycle> filterBicycles(String location, String type, double maxPrice, boolean available) {
		// Implementa la lógica para filtrar bicicletas según la ubicación, tipo, precio
		// y disponibilidad
		return bicycleRepository.findByLocationAndTypeAndPriceLessThanEqualAndAvailable(location, type, maxPrice,
				available);
	}
}

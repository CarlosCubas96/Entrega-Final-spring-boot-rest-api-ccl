// RentalServiceImpl.java
package com.proyectdwes.api.proyect.services;

import com.proyectdwes.api.proyect.models.Bicycle;
import com.proyectdwes.api.proyect.models.Rental;
import com.proyectdwes.api.proyect.models.User;
import com.proyectdwes.api.proyect.repository.BicycleRepository;
import com.proyectdwes.api.proyect.repository.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RentalServiceImpl implements RentalServiceI {

	@Autowired
	private RentalRepository rentalRepository;

	@Autowired
	private BicycleRepository bicycleRepository;

	@Autowired
	private BicycleServiceI bicycleService;

	@Override
	public Rental createRental(Long userId, Long bicycleId, LocalDateTime startTime, LocalDateTime endTime) {
		// Verificar si la bicicleta está disponible
		Bicycle bicycle = bicycleService.getBicycleById(bicycleId);
		if (bicycle == null || !bicycle.isAvailable()) {
			throw new IllegalArgumentException("La bicicleta no está disponible para alquilar");
		}

		// Calcular el costo del alquiler
		double hourlyRate = bicycle.getHourlyRate();
		double totalCost = calcularCostoAlquiler(startTime, endTime, hourlyRate);

		// Crear la transacción de alquiler
		Rental rental = new Rental();

		// Puedes obtener el usuario desde el UserRepository o algún servicio de usuario
		User user = new User();
		user.setId(userId);

		rental.setUser(user);
		rental.setBicycle(bicycle);
		rental.setStartTime(startTime);
		rental.setEndTime(endTime);
		rental.setTotalCost(totalCost);

		// Marcar la bicicleta como no disponible durante el período de alquiler
		bicycle.setAvailable(false);
		bicycleRepository.save(bicycle);

		// Guardar la transacción de alquiler en la base de datos
		return rentalRepository.save(rental);
	}

	@Override
	public Rental getRentalById(Long rentalId) {
		return rentalRepository.findById(rentalId).orElse(null);
	}

	@Override
	public List<Rental> getAllRentals() {
		return rentalRepository.findAll();
	}

	@Override
	public List<Rental> getRentalHistory(Long userId) {
		// Puedes obtener el usuario desde el UserRepository o algún servicio de usuario
		User user = new User();
		user.setId(userId);
		return rentalRepository.findByUser(user);
	}

	@Override
	public Rental updateRental(Long rentalId, LocalDateTime endTime) {
		Optional<Rental> existingRental = rentalRepository.findById(rentalId);
		if (existingRental.isPresent()) {
			Rental rental = existingRental.get();
			// Actualizar la fecha de finalización del alquiler
			rental.setEndTime(endTime);
			return rentalRepository.save(rental);
		} else {
			// Maneja el caso en que el alquiler no exista
			throw new IllegalArgumentException("No se encontró el alquiler con ID " + rentalId);
		}
	}

	@Override
	public void deleteRental(Long rentalId) {
		Optional<Rental> existingRental = rentalRepository.findById(rentalId);
		if (existingRental.isPresent()) {
			Rental rental = existingRental.get();
			// Marcar la bicicleta como disponible
			Bicycle bicycle = rental.getBicycle();
			bicycle.setAvailable(true);
			bicycleRepository.save(bicycle);

			// Eliminar el registro de alquiler
			rentalRepository.deleteById(rentalId);
		} else {
			// Maneja el caso en que el alquiler no exista
			throw new IllegalArgumentException("No se encontró el alquiler con ID " + rentalId);
		}
	}

	private double calcularCostoAlquiler(LocalDateTime startTime, LocalDateTime endTime, double hourlyRate) {
		return Duration.between(startTime, endTime).toHours() * hourlyRate;
	}

}

package com.proyectdwes.api.proyect.services;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyectdwes.api.proyect.models.Bicycle;
import com.proyectdwes.api.proyect.models.Rental;
import com.proyectdwes.api.proyect.models.User;
import com.proyectdwes.api.proyect.repository.BicycleRepository;
import com.proyectdwes.api.proyect.repository.RentalRepository;

@Service
public class RentalServiceImpl {

	@Autowired
	private RentalRepository rentalRepository;

	@Autowired
	private BicycleRepository bicycleRepository;

	@Autowired
	private BicycleServiceI bicycleService;

	public Rental rentBicycle(User user, Long bicycleId, LocalDateTime startTime, LocalDateTime endTime) {
		// Verificar si la bicicleta está disponible
		Bicycle bicycle = bicycleService.getBicycleDetails(bicycleId);
		if (bicycle == null || !bicycle.isAvailable()) {
			throw new IllegalArgumentException("La bicicleta no está disponible para alquilar");
		}

		// Calcular el costo del alquiler
		double hourlyRate = bicycle.getHourlyRate();
		double totalCost = calcularCostoAlquiler(startTime, endTime, hourlyRate);

		// Crear la transacción de alquiler
		Rental rental = new Rental();
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

	public List<Rental> getRentalHistory(User user) {
		// Obtener el historial de alquileres de un usuario específico
		return rentalRepository.findByUser(user);
	}

	public void returnBicycle(Long rentalId) {
		// Implementar la lógica para devolver la bicicleta, actualizar la
		// disponibilidad y finalizar el alquiler
		// Puedes agregar más lógica según tus necesidades específicas.
	}

	private double calcularCostoAlquiler(LocalDateTime startTime, LocalDateTime endTime, double hourlyRate) {
		// Implementar la lógica para calcular el costo total del alquiler
		// Puedes agregar más lógica según tus necesidades específicas.
		long hours = Duration.between(startTime, endTime).toHours();
		return hours * hourlyRate;
	}
}

package com.proyectdwes.api.proyect.services;

import com.proyectdwes.api.proyect.models.Rental;

import java.time.LocalDateTime;
import java.util.List;

public interface RentalServiceI {

	Rental createRental(Long userId, Long bicycleId, LocalDateTime startTime, LocalDateTime endTime);

	Rental getRentalById(Long rentalId);

	List<Rental> getAllRentals();

	List<Rental> getRentalHistory(Long userId);

	Rental updateRental(Long rentalId, LocalDateTime endTime);

	void deleteRental(Long rentalId);
}

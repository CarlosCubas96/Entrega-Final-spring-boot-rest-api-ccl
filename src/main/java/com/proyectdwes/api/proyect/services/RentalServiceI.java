package com.proyectdwes.api.proyect.services;

import java.time.LocalDateTime;
import java.util.List;

import com.proyectdwes.api.proyect.models.Rental;

public interface RentalServiceI {

	/**
	 * Realiza el alquiler de una bicicleta para un usuario dado.
	 *
	 * @param user      El usuario que realiza el alquiler.
	 * @param bicycleId Identificador de la bicicleta a alquilar.
	 * @param startTime Fecha y hora de inicio del alquiler.
	 * @param endTime   Fecha y hora de fin del alquiler.
	 * @return La transacción de alquiler creada.
	 * @throws IllegalArgumentException Si la bicicleta no está disponible para
	 *                                  alquilar.
	 */
	Rental rentBicycle(Long userId, Long bicycleId, LocalDateTime startTime, LocalDateTime endTime);

	/**
	 * Obtiene el historial de alquileres para un usuario dado.
	 *
	 * @param userId El usuario para el cual se obtiene el historial.
	 * @return Lista de transacciones de alquiler del usuario.
	 */
	List<Rental> getRentalHistory(Long userId);

	/**
	 * Procesa la devolución de una bicicleta y finaliza el alquiler.
	 *
	 * @param rentalId Identificador de la transacción de alquiler.
	 * @throws IllegalArgumentException Si la transacción de alquiler no existe o ya
	 *                                  ha sido procesada.
	 */
	void returnBicycle(Long rentalId);
}

package com.proyectdwes.api.proyect.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyectdwes.api.proyect.models.Rental;
import com.proyectdwes.api.proyect.models.User;

public interface RentalRepository extends JpaRepository<Rental, Long> {
	List<Rental> findByUser(User user);
}

package com.proyectdwes.api.proyect.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyectdwes.api.proyect.models.Rental;
import com.proyectdwes.api.proyect.models.User;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Long> {
	List<Rental> findByUser(User user);
}

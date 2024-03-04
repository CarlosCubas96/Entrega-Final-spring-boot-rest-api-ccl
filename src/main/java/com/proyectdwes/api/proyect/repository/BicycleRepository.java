package com.proyectdwes.api.proyect.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyectdwes.api.proyect.models.Bicycle;

public interface BicycleRepository extends JpaRepository<Bicycle, Long> {
	List<Bicycle> findByAvailableTrue();

	List<Bicycle> findByLocationAndTypeAndPriceLessThanEqualAndAvailable(String location, String type, double maxPrice,
			boolean available);
}
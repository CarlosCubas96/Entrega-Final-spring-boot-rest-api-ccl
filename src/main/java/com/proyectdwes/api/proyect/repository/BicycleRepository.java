package com.proyectdwes.api.proyect.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyectdwes.api.proyect.models.Bicycle;

@Repository
public interface BicycleRepository extends JpaRepository<Bicycle, Long> {

}
// RentalController.java
package com.proyectdwes.api.proyect.controllers;

import com.proyectdwes.api.proyect.models.Rental;
import com.proyectdwes.api.proyect.services.RentalServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/rentals")
public class RentalController {

    @Autowired
    private RentalServiceI rentalService;

    @PostMapping("/createRental")
    public Rental createRental(
            @RequestParam Long userId,
            @RequestParam Long bicycleId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startTime,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endTime
    ) {
        return rentalService.createRental(userId, bicycleId, startTime, endTime);
    }

    @GetMapping("/all")
    public List<Rental> getAllRentals() {
        return rentalService.getAllRentals();
    }

    @GetMapping("/{rentalId}")
    public Rental getRentalById(@PathVariable Long rentalId) {
        return rentalService.getRentalById(rentalId);
    }

    @GetMapping("/rentalHistory/{userId}")
    public List<Rental> getRentalHistory(@PathVariable Long userId) {
        return rentalService.getRentalHistory(userId);
    }

    @PutMapping("/updateRental/{rentalId}")
    public Rental updateRental(
            @PathVariable Long rentalId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endTime
    ) {
        return rentalService.updateRental(rentalId, endTime);
    }

    @DeleteMapping("/deleteRental/{rentalId}")
    public void deleteRental(@PathVariable Long rentalId) {
        rentalService.deleteRental(rentalId);
    }
}

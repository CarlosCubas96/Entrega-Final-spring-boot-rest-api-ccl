package com.proyectdwes.api.proyect.config;

import java.time.LocalDateTime;
import java.util.Locale;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.proyectdwes.api.proyect.models.Bicycle;
import com.proyectdwes.api.proyect.models.Rental;
import com.proyectdwes.api.proyect.models.Role;
import com.proyectdwes.api.proyect.models.User;
import com.proyectdwes.api.proyect.repository.BicycleRepository;
import com.proyectdwes.api.proyect.repository.RentalRepository;
import com.proyectdwes.api.proyect.repository.UserRepository;
import com.github.javafaker.Faker;

@Profile("demo")
@Component
public class InitializationData implements CommandLineRunner {

	private final UserRepository userRepository;
	private final BicycleRepository bicycleRepository;
	private final RentalRepository rentalRepository;
	private final PasswordEncoder passwordEncoder;

	private final boolean deleteData = true; // Variable para controlar el borrado de datos

	public InitializationData(UserRepository userRepository, BicycleRepository bicycleRepository,
			RentalRepository rentalRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.bicycleRepository = bicycleRepository;
		this.rentalRepository = rentalRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public void run(String... args) throws Exception {

		if (deleteData) {
			userRepository.deleteAll();
			bicycleRepository.deleteAll();
			rentalRepository.deleteAll();
		}

		try {
			// Usuario 1 - Rol USER
			User user1 = new User();
			user1.setFirstName("Carlos");
			user1.setLastName("Cubas");
			user1.setEmail("carloscubaf12@gmail.com");
			user1.setPassword(passwordEncoder.encode("prueba1"));
			user1.getRoles().add(Role.ROLE_USER);
			userRepository.save(user1);

			// Usuario 2 - Rol ADMIN
			User user2 = new User();
			user2.setFirstName("Pablo");
			user2.setLastName("Jimenez");
			user2.setEmail("pabloj32@gmail.com");
			user2.setPassword(passwordEncoder.encode("prueba2"));
			user2.getRoles().add(Role.ROLE_ADMIN);
			userRepository.save(user2);

		} catch (Exception e) {

		}

		Faker faker = new Faker(new Locale("es"));
		for (int i = 0; i < 10; i++) { // Generar 10 bicicletas ficticias
			Bicycle bicycle = new Bicycle();
			bicycle.setModel(faker.funnyName().name());
			bicycle.setHourlyRate(faker.number().randomDouble(2, 1, 10));
			bicycle.setAvailable(faker.bool().bool());
			bicycleRepository.save(bicycle);
		}

		for (int i = 0; i < 5; i++) { // Generar 5 alquileres ficticios
			Rental rental = new Rental();
			rental.setUser(userRepository.findAll().get(i % 2)); // 2 usuarios creados
			rental.setBicycle(bicycleRepository.findAll().get(i % 10)); // 10 bicicletas creadas
			rental.setStartTime(LocalDateTime.now()); // Establecer StartTime como el tiempo actual
			rental.setEndTime(LocalDateTime.now().plusDays(1)); // Establecer EndTime como el tiempo actual más un día
			rentalRepository.save(rental);
		}
	}
}

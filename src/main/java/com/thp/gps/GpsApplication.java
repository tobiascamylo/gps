package com.thp.gps;

import com.thp.gps.model.PontosInteresse;
import com.thp.gps.repository.PontosInteresseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
/**
 * @author Tobias Correa
 * @version 1.0
 * @since 2025-04-27
 *
 * This is the main application class for the GPS application.
 * It initializes the Spring Boot application and populates the database with sample data.
 */
public class GpsApplication implements CommandLineRunner {

	@Autowired
	private PontosInteresseRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(GpsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		repository.save(new PontosInteresse("Lanchonete", 27L, 12L));
		repository.save(new PontosInteresse("Posto", 31L, 18L));
		repository.save(new PontosInteresse("Joalheria", 15L, 12L));
		repository.save(new PontosInteresse("Floricultura", 19L, 21L));
		repository.save(new PontosInteresse("Pub", 12L, 8L));
		repository.save(new PontosInteresse("Supermercado", 23L, 6L));
		repository.save(new PontosInteresse("Churrascaria", 28L, 2L));
		repository.save(new PontosInteresse("Quadra de Volei", 32L, 21L));

	}
}

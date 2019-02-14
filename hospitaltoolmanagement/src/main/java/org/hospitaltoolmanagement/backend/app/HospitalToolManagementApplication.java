package org.hospitaltoolmanagement.backend.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("org.hospitaltoolmanagement.backend.model")
@ComponentScan({"org.hospitaltoolmanagement.backend.services", "org.hospitaltoolmanagement.backend.controllers", "org.hospitaltoolmanagement.backend.utilities"})
@EnableJpaRepositories("org.hospitaltoolmanagement.backend.repositories")
public class HospitalToolManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(HospitalToolManagementApplication.class, args);
	}
}

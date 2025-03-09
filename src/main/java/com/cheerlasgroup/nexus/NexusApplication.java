package com.cheerlasgroup.nexus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = { com.cheerlasgroup.nexus.repository.UserDetailsRepository.class })
public class NexusApplication {

	public static void main(String[] args) {
		SpringApplication.run(NexusApplication.class, args);
	}

}

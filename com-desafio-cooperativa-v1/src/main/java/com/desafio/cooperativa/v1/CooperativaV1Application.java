package com.desafio.cooperativa.v1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = {"com.desafio.cooperativa.v1.domain.repositorios"})
@EntityScan(basePackages = {"com.desafio.cooperativa.v1.domain.entidades"})
@SpringBootApplication
public class CooperativaV1Application {

	public static void main(String[] args) {
		SpringApplication.run(CooperativaV1Application.class, args);
	}

}

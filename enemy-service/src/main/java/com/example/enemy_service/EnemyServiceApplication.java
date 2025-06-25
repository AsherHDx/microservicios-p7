package com.example.enemy_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class EnemyServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EnemyServiceApplication.class, args);
	}

	@GetMapping("/enemies/test")
	public String test() {
		return "El servicio de jugadores está funcionando";
	}

}

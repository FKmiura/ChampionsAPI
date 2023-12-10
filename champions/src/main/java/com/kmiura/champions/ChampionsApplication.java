package com.kmiura.champions;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.kmiura.champions.domain")
public class ChampionsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChampionsApplication.class, args);
	}

}

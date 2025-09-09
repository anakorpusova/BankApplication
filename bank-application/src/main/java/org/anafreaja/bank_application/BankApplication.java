package org.anafreaja.bank_application;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class BankApplication {

	public static void main(String[] args) {

		SpringApplication.run(BankApplication.class, args);
	}

	@Bean
	CommandLineRunner probeDb(JdbcTemplate jdbc){
		return args -> {
			Integer one = jdbc.queryForObject("SELECT 1", Integer.class);
			System.out.println("DB probe result: " + one);
		};
	}

}

package com.example.manage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableScheduling
@SpringBootApplication
public class ManageAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(ManageAppApplication.class, args);
	}

}

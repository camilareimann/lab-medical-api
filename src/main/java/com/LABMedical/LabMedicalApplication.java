package com.LABMedical;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class LabMedicalApplication {

	public static void main(String[] args) {
		SpringApplication.run(LabMedicalApplication.class, args);
	}

}
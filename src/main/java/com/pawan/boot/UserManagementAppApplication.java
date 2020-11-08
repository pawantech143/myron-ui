package com.pawan.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.pawan.boot.repository.CountryMasterRepository;

@SpringBootApplication
public class UserManagementAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserManagementAppApplication.class, args);
		
	}
	
}

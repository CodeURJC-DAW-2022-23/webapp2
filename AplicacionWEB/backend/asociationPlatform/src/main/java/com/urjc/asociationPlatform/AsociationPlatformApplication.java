package com.urjc.asociationPlatform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.urjc.asociationPlatform.repository.CustomEventRepositoryImpl;


@SpringBootApplication
public class AsociationPlatformApplication {

	public static void main(String[] args) {
		SpringApplication.run(AsociationPlatformApplication.class, args); 
	}
}

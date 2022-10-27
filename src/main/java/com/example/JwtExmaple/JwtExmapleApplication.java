package com.example.JwtExmaple;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "")
@EnableAutoConfiguration
@EnableConfigurationProperties
@SpringBootApplication
public class JwtExmapleApplication {

	public static void main(String[] args) {
		SpringApplication.run(JwtExmapleApplication.class, args);
	}

}

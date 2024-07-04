package com.secured.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan("com")
public class AuthApplication {

	public static void main(String[] args) {
		System.out.println("222234.1");
		SpringApplication.run(AuthApplication.class, args);
	}

}

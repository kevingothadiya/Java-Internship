package com.example.customquerymethods;

import com.github.javafaker.Faker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CustomquerymethodsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomquerymethodsApplication.class, args);
	}

    @Bean
    public Faker myFaker(){
        return new Faker();
    }
}

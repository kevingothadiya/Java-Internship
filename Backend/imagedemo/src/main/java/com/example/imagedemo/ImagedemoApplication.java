package com.example.imagedemo;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ImagedemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ImagedemoApplication.class, args);
	}

    @Bean
    public ModelMapper myModelMapper(){
        return new ModelMapper();
    }
}

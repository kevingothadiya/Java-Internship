package com.example.onetoonebidirectional;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.management.modelmbean.ModelMBean;

@SpringBootApplication
public class OnetoonebidirectionalApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnetoonebidirectionalApplication.class, args);
	}

    @Bean
    public ModelMapper myModelMapper(){
        return new ModelMapper();
    }
}

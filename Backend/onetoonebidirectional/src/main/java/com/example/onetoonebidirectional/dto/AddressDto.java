package com.example.onetoonebidirectional.dto;

import com.example.onetoonebidirectional.domain.Address;
import com.example.onetoonebidirectional.domain.Student;
import lombok.Data;

@Data
public class AddressDto {

    private Long id;
    private String street;
    private String city;
    private String country;
    private StudentDto student;

}

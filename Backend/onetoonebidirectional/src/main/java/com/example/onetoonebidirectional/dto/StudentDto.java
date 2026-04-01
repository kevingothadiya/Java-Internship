package com.example.onetoonebidirectional.dto;

import com.example.onetoonebidirectional.domain.Address;
import lombok.Data;

@Data
public class StudentDto {

    private Long id;
    private String name;
    private String email;
    private AddressDto address;
}

package com.example.onetomanystudentmapping.utility;

import com.example.onetomanystudentmapping.dto.AddressDto;
import com.example.onetomanystudentmapping.dto.StudentDto;
import com.example.onetomanystudentmapping.entity.Address;
import com.example.onetomanystudentmapping.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

@Component
public class ModelMapility {

    @Autowired
    private ObjectMapper objectMapper;

    public Student getStudentEntity(StudentDto studentDto){
        return objectMapper.convertValue(studentDto,Student.class);
    }

    public StudentDto getStudentDto(Student student){
        student.getAddress().forEach(x->x.setStudent(null));
        return objectMapper.convertValue(student,StudentDto.class);
    }

    public Address getAddressEntity(AddressDto addressDto){
        return objectMapper.convertValue(addressDto, Address.class);
    }

    public AddressDto getAddressDto(Address address){
        address.getStudent().setAddress(null);
        return objectMapper.convertValue(address, AddressDto.class);
    }

}

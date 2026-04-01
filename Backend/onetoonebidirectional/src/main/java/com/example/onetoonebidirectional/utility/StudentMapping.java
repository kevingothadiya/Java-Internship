package com.example.onetoonebidirectional.utility;

import com.example.onetoonebidirectional.domain.Address;
import com.example.onetoonebidirectional.domain.Student;
import com.example.onetoonebidirectional.dto.AddressDto;
import com.example.onetoonebidirectional.dto.StudentDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component

public class StudentMapping {

    @Autowired
    private ModelMapper modelMapper;

    public StudentDto stdEntityToStdDto(Student student){
        student.getAddress().setStudent(null);
        return modelMapper.map(student,StudentDto.class);
    }

    public Student stdDtoToStdEntity(StudentDto studentDto){
        return modelMapper.map(studentDto,Student.class);
    }

    public List<StudentDto> listStdEntityTolistStdDto(List<Student> student){
//      return student.stream().map(x-> modelMapper.map(x,StudentDto.class)).toList();


        return student.stream().map(this::stdEntityToStdDto).toList();
    }

    public List<Student> listStdDtoToListStdEntity(List<StudentDto> studentDtos){
        return studentDtos.stream().map(x->stdDtoToStdEntity(x)).toList();
    }

    public AddressDto addEntityToaddDto(Address address){
        address.getStudent().setAddress(null);
        return modelMapper.map(address,AddressDto.class);
    }

    public Address addDtoToaddEntity(AddressDto addressDto){
        Address address = modelMapper.map(addressDto, Address.class);
        if(address.getStudent()!=null){
            address.getStudent().setAddress(address);
        }
        return address;
    }

    public List<AddressDto> addListEntityToAddListDto(List<Address> addresses){
        //return addresses.stream().map(s->modelMapper.map(s, AddressDto.class)).toList();
        return addresses.stream().map(this::addEntityToaddDto).toList();
    }

    public  List<Address> addListDtoToaddListEntity(List<AddressDto> addressDtos){
        return addressDtos.stream().map(this::addDtoToaddEntity).toList();
    }
}

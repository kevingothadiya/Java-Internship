package com.example.manytomanydemo.utility;

import com.example.manytomanydemo.dto.CourceDto;
import com.example.manytomanydemo.dto.StudentDto;
import com.example.manytomanydemo.entity.Cource;
import com.example.manytomanydemo.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

import java.util.List;

@Component
public class ModelMapper {

    @Autowired
    private ObjectMapper objectMapper;

    public Student getStudentEntity(StudentDto studentDto){
        return objectMapper.convertValue(studentDto,Student.class);
    }

    public StudentDto getStudentDto(Student student){
        student.getCources().forEach(x->x.setStudents(null  ));
        return objectMapper.convertValue(student, StudentDto.class);
    }

    public List<Student> getListStudentEntity(List<StudentDto> studentDtos){
        return studentDtos.stream().map(this::getStudentEntity).toList();
    }

    public List<StudentDto> getListStudentDto(List<Student> students){
        return students.stream().map(this::getStudentDto).toList();
    }

    public Cource getCourceEntity(CourceDto courceDto){
        return objectMapper.convertValue(courceDto, Cource.class);
    }

    public CourceDto getCourceDto(Cource cource){
        cource.getStudents().forEach(x->x.setCources(null));
        return objectMapper.convertValue(cource, CourceDto.class);
    }

    public List<Cource> getListCourceEntity(List<CourceDto> courceDtos){
        return courceDtos.stream().map(this::getCourceEntity).toList();
    }

    public List<CourceDto> getListCourceDto(List<Cource> cources){
        return cources.stream().map(this::getCourceDto).toList();
    }
}

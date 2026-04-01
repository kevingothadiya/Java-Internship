package com.example.customquerymethods.helper;

import com.example.customquerymethods.dto.StudentDto;
import com.example.customquerymethods.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

import java.util.List;

@Component
public class MapperHelper {

    @Autowired
    public ObjectMapper objectMapper;

    public Student getStudentEntity(StudentDto studentDto){
        return objectMapper.convertValue(studentDto, Student.class);
    }

    public StudentDto getStudentDto(Student student){
        return objectMapper.convertValue(student, StudentDto.class);
    }

    public List<Student> getListStudent(List<StudentDto> studentDtos){
        return studentDtos.stream().map(this::getStudentEntity).toList();
    }

    public List<StudentDto> getListStudentDto(List<Student> students){
        return students.stream().map(this::getStudentDto).toList();
    }
}

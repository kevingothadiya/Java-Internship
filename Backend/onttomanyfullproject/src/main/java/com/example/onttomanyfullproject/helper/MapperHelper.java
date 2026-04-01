package com.example.onttomanyfullproject.helper;

import com.example.onttomanyfullproject.dto.StudentDto;
import com.example.onttomanyfullproject.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

import java.util.List;

@Component
public class MapperHelper {

    @Autowired
    private ObjectMapper mapper;

    public Student getStudentEntity(StudentDto studentDto){
//        studentDto.getAddresses().forEach(x->x.setStudent(studentDto));
        return mapper.convertValue(studentDto, Student.class);
    }

    public StudentDto getStudentDto(Student student){
        student.getAddresses().forEach(x->x.setStudent(null));
        return mapper.convertValue(student, StudentDto.class);
    }

    public List<Student> getListStudentEntity(List<StudentDto> studentDtos){
        return studentDtos.stream().map(this::getStudentEntity).toList();
    }

    public List<StudentDto> getListStudentDto(List<Student> students){
        return students.stream().map(this::getStudentDto).toList();
    }
}

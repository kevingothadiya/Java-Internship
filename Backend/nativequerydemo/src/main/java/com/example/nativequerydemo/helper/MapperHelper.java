package com.example.nativequerydemo.helper;

import com.example.nativequerydemo.dto.StudentDto;
import com.example.nativequerydemo.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

import java.util.List;

@Component
public class MapperHelper {

    @Autowired
    private ObjectMapper mapper;

    public Student getStudentEntity(StudentDto studentDto){
        return mapper.convertValue(studentDto, Student.class);
    }

    public StudentDto getStudentDto(Student student){
        return mapper.convertValue(student,StudentDto.class);
    }

    public List<Student> getListStudentEntity(List<StudentDto> studentDtos){
        return studentDtos.stream().map(this::getStudentEntity).toList();
    }

    public List<StudentDto> getListStuidentDto(List<Student> students){
        return students.stream().map(this::getStudentDto).toList();
    }
}

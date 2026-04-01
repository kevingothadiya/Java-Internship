package com.example.onetoonebidirectional.service.implementation;

import com.example.onetoonebidirectional.domain.Student;
import com.example.onetoonebidirectional.dto.StudentDto;
import com.example.onetoonebidirectional.repository.StudentRepository;
import com.example.onetoonebidirectional.service.StudentService;
import com.example.onetoonebidirectional.utility.StudentMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepo;

    @Autowired
    private StudentMapping studentMapping;

    @Override
    public String saveData(StudentDto studentDto) {
        return studentRepo.save(studentMapping.stdDtoToStdEntity(studentDto)).toString();
    }

    @Override
    public List<StudentDto> getAllData() {

//        List<Student> all = studentRepo.findAll();
//        List<StudentDto> studentDtos = studentMapping.listStdEntityTolistStdDto(all);
//        return studentDtos;

        return studentMapping.listStdEntityTolistStdDto(studentRepo.findAll());
    }

    @Override
    public StudentDto getDataById(Long id) {
        Optional<Student> byId = studentRepo.findById(id);
        return byId.map(student -> studentMapping.stdEntityToStdDto(student)).orElse(null);
//        Student student = studentRepo.findById(id).get();
//        StudentDto studentDto = studentMapping.stdEntityToStdDto(student);
//        return studentDto;

       // return studentMapping.stdEntityToStdDto(studentRepo.findById(id).get());

    }
}

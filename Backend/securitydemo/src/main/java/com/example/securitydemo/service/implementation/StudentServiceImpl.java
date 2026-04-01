package com.example.securitydemo.service.implementation;

import com.example.securitydemo.dto.StudentDto;
import com.example.securitydemo.helper.MapperHelper;
import com.example.securitydemo.repository.StudentRepo;
import com.example.securitydemo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private MapperHelper helper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public String addStudent(StudentDto studentDto) {
        studentDto.setPassword(passwordEncoder.encode(studentDto.getPassword()));
        return studentRepo.save(helper.getStudentEntity(studentDto)).toString();

    }
}

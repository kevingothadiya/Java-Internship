package com.example.manytomanydemo.service.implementation;

import com.example.manytomanydemo.dto.StudentDto;
import com.example.manytomanydemo.entity.Student;
import com.example.manytomanydemo.repository.StudentRepo;
import com.example.manytomanydemo.service.StudentService;
import com.example.manytomanydemo.utility.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private ModelMapper modelMapper;
    
    @Autowired 
    private StudentRepo studentRepo;
    
    @Override
    public String saveAll(StudentDto studentDto) {
        Student studentEntity = modelMapper.getStudentEntity(studentDto);
        studentRepo.save(studentEntity);
        return "Successfully Saved";
    }

    @Override
    public List<StudentDto> getAllStudent() {
        List<Student> all = studentRepo.findAll();
        List<StudentDto> listStudentDto = modelMapper.getListStudentDto(all);
        return listStudentDto;
    }

    @Override
    public List<StudentDto> getByDepartment(String department) {
        List<Student> byDepartment = studentRepo.findByDepartment(department);
        return modelMapper.getListStudentDto(byDepartment);
    }

    @Override
    public List<StudentDto> getByName(String name) {
        List<Student> byName = studentRepo.findByName(name);
        return modelMapper.getListStudentDto(byName);
    }
}

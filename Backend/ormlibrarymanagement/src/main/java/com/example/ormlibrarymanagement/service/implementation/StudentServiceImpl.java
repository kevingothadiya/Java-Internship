package com.example.ormlibrarymanagement.service.implementation;

import com.example.ormlibrarymanagement.entity.Student;
import com.example.ormlibrarymanagement.repository.StudentRepo;
import com.example.ormlibrarymanagement.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepo studentRepo;

    @Override
    public String addStudent(Student student) {
        studentRepo.save(student);
        return "Successfully Saved";
    }

    @Override
    public List<Student> getAllStudent() {
        return studentRepo.findAll();
    }

    @Override
    public String deleteStudentById(Long id) {
        studentRepo.deleteById(id);
        return "Successfully Delete Student of ID : " + id;
    }

    @Override
    public Student getStudentById(Long id) {
        return studentRepo.findById(id).orElseThrow(()->new RuntimeException("No Student Found with ID : " + id));
    }

    @Override
    public String updateStudent(Long id, Student student) {
        Optional<Student> byId = studentRepo.findById(id);
        if(byId.isPresent()){
            student.setId(student.getId());
            studentRepo.save(student);
            return "Update Successfully";
        }
        else {
            throw new RuntimeException("No Student Found With ID : " + id);
        }
    }
}

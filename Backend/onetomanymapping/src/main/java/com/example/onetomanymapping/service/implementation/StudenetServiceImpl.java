package com.example.onetomanymapping.service.implementation;

import com.example.onetomanymapping.domain.Student;
import com.example.onetomanymapping.repository.StudentRepo;
import com.example.onetomanymapping.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudenetServiceImpl implements StudentService {

    @Autowired
    private StudentRepo studentRepo;

    @Override
    public String saveData(Student std) {
        Student save = studentRepo.save(std);
        return save.toString();
    }

    @Override
    public List<Student> getAllData() {
        return studentRepo.findAll();
    }

    @Override
    public Student getDataByEmailId(String email){
        return studentRepo.findByEmailId(email);
    }

    @Override
    public String updateData(Student std, Long id) {
        Optional<Student> byId = studentRepo.findById(id);
        if(byId.isPresent()){
            Student student = byId.get();
            std.setId(student.getId());

        }
    }

    @Override
    public String removeDataById(Long id) {
        if(studentRepo.findById(id).isPresent()){
            studentRepo.deleteById(id);
            return "Remove Data of ID : " + id;
        }
        else {
            return "Not ID Found";
        }
    }

    @Override
    public String removeAll() {
        studentRepo.deleteAll();
        return "Successfully Deleted";
    }
}

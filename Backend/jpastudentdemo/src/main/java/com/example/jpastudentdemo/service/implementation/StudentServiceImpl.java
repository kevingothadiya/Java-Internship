package com.example.jpastudentdemo.service.implementation;

import com.example.jpastudentdemo.domain.StudentDomain;
import com.example.jpastudentdemo.repository.StudentRepository;
import com.example.jpastudentdemo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository stdrepo;

    @Override
    public String saveData(StudentDomain std) {
        StudentDomain save = stdrepo.save(std);
        return save.toString();
    }

    @Override
    public List<StudentDomain> getAllStudentData() {
        return stdrepo.findAll();
    }

    @Override
    public StudentDomain getByEmail(String email) {
        return stdrepo.findByEmailId(email);
    }

    @Override
    public String removeById(Long id) {
        if(stdrepo.findById(id).isPresent()){
            stdrepo.deleteById(id);
            return "Successfully Removed Data of Id " + id;
        }
        else {
            return "No Data Found Data With ID"+id;
        }
    }

    @Override
    public String update(StudentDomain std, Long id) {
        Optional<StudentDomain> byId = stdrepo.findById(id);
        if(byId.isPresent()){
            StudentDomain studentDomain = byId.get();
            std.setId(studentDomain.getId());
            std.getPersonalInformation().setId(studentDomain.getPersonalInformation().getId());
            return stdrepo.save(std).toString();
        }
        else {
            return "No Data Found With ID"+id;
        }
    }

    @Override
    public String removeAll() {
        if(stdrepo.findAll().isEmpty()){
            return "No Data Available";
        }
        else {
            stdrepo.deleteAll();
            return "Deleted Successfully";
        }
    }

}

package com.example.globalexceptiondemo.service.implementation;

import com.example.globalexceptiondemo.custom.exception.NoStudentFoundException;
import com.example.globalexceptiondemo.entity.Student;
import com.example.globalexceptiondemo.proxy.StudentProxy;
import com.example.globalexceptiondemo.repository.StudentRepo;
import com.example.globalexceptiondemo.service.StudentService;
import com.example.globalexceptiondemo.utility.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private StudentRepo studentRepo;

    @Override
    public String SaveAll(StudentProxy studentProxy) {
        Student studentEntity = modelMapper.getStudentEntity(studentProxy);
        studentEntity.getAddress().forEach(x->x.setStudent(studentEntity));
        studentRepo.save(studentEntity);
          return "Successfully Saved";
    }

    @Override
    public List<StudentProxy> getAllStudent() {
        if(studentRepo.findAll().isEmpty()){
            throw new NoStudentFoundException("No Student Available",HttpStatus.NOT_FOUND.value());
        }
        return modelMapper.getListStudentProxy(studentRepo.findAll());
    }

    @Override
    public List<StudentProxy> getStudentByName(String name) {
        if(studentRepo.findByName(name).isEmpty()){
            throw new RuntimeException("No Student Found With Name");
        }
        else {
            return modelMapper.getListStudentProxy(studentRepo.findByName(name));
        }
    }

    @Override
    public String updateStudent(StudentProxy studentProxy, Long id) {
        Optional<Student> byId = studentRepo.findById(id);
        if(byId.isPresent()){
            studentRepo.save(modelMapper.getStudentEntity(studentProxy));
            return "Successfully Updated";
        }
        else {
            throw new NoStudentFoundException("No Student Found With ID : " + id, HttpStatus.NOT_FOUND.value());
        }
    }

    @Override
    public String removeById(Long id) {
        if(studentRepo.findById(id).isPresent()){
            studentRepo.deleteById(id);
            return "Successfully Remove Data Of ID : " + id;
        }
        else {
            throw new NoStudentFoundException("No Student Found With Id : " + id,HttpStatus.NOT_FOUND.value());
        }
    }

    @Override
    public String removeAll() {
        if(studentRepo.findAll().isEmpty()){
            throw new NoStudentFoundException("No Student Available",HttpStatus.NOT_FOUND.value());
        }
        else {
            studentRepo.deleteAll();
            return "Removed All Data Successfully";
        }
    }
}

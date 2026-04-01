package com.example.ormstudentdemo.service.implementation;

import com.example.ormstudentdemo.entity.StudentEntity;
import com.example.ormstudentdemo.repository.StudentRepo;
import com.example.ormstudentdemo.service.StudentInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.prefs.PreferenceChangeListener;

@Service
public class StudentImpl implements StudentInterface {

    @Autowired
    private StudentRepo studentRepo;

    @Override
    public String saveData(StudentEntity std) {
        StudentEntity save = studentRepo.save(std);
        return save.toString();
    }

    @Override
    public List<StudentEntity> gatData() {
        return studentRepo.findAll();
    }

    @Override
    public StudentEntity getDataById(Long id) {
        if(studentRepo.findById(id).isPresent()){
            return studentRepo.findById(id).get();
        }
        else {
            return new StudentEntity(null,null,null,null);
        }
    }

    @Override
    public String updateData(StudentEntity std, Long id) {
        Optional<StudentEntity> byId = studentRepo.findById(id);
        if (byId.isPresent()){
            StudentEntity studentEntity = byId.get();
            studentEntity.setId(std.getId());
            studentEntity.setName(std.getName());
            studentEntity.setDepartment(std.getDepartment());
            studentEntity.setCgpa(std.getCgpa());

            return "Successfully Updated";
        }
        else {
            return "No Data Found With ID " + id;
        }
    }

    @Override
    public String removeDataById(Long id) {
        if(studentRepo.findById(id).isPresent()){
            studentRepo.deleteById(id);
            return "Successfully Removed Data With ID " + id;
        }
        else {
            return "No Data Found with ID " + id;
        }
    }

    @Override
    public String removeData() {
        if(studentRepo.findAll().isEmpty()){
            return "No Student Data Available";
        }
        else {
            studentRepo.deleteAll();;
            return "Successfully Removed";
        }
    }
}

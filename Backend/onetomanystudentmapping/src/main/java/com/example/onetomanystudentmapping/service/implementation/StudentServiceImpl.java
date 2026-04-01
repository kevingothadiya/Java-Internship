package com.example.onetomanystudentmapping.service.implementation;

import com.example.onetomanystudentmapping.dto.StudentDto;
import com.example.onetomanystudentmapping.entity.Student;
import com.example.onetomanystudentmapping.repository.StudentRepository;
import com.example.onetomanystudentmapping.service.StudentService;
import com.example.onetomanystudentmapping.utility.ModelMapility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepo;

    @Autowired
    private ModelMapility modelMapility;

    @Override
    public String saveStdData(StudentDto studentDto) {
        Student studentEntity = modelMapility.getStudentEntity(studentDto);
        studentEntity.getAddress().forEach(x->x.setStudent(studentEntity));
        studentRepo.save(studentEntity);
        return "Saved";
    }

    @Override
    public List<StudentDto> getAllStudent() {
        List<Student> all = studentRepo.findAll();
        return all.stream().map(x -> modelMapility.getStudentDto(x)).toList();
    }

    @Override
    public StudentDto getStdById(Long id) {
        if(studentRepo.findById(id).isPresent()){
            Student student = studentRepo.findById(id).get();
            StudentDto studentDto = modelMapility.getStudentDto(student);
            return studentDto;
        }
        else {
            return new StudentDto();
        }
    }

    @Override
    public String updateStudent(StudentDto studentDto, Long id) {
        Optional<Student> byId = studentRepo.findById(id);
        if(byId.isPresent()){
            Student studentEntity = modelMapility.getStudentEntity(studentDto);
            studentEntity.getAddress().forEach(s -> s.setStudent(studentEntity));
            studentRepo.save(studentEntity);
            return "Successfully Updated";
        }
        else {
            return "No Data Found Of Id : " + id;
        }
    }

    @Override
    public String removeStdById(Long id) {
        if(studentRepo.findById(id).isPresent()){
            studentRepo.deleteById(id);
            return "Successfully removed Data of Id : " + id;
        }
        else {
            return "No Data Found of Id : " + id;
        }
    }

    @Override
    public String removeAllStd() {
        if(studentRepo.findAll().isEmpty()){
            return "No Data Available";
        }
        else {
            studentRepo.deleteAll();
            return "Successfully Removed All Data";
        }
    }
}

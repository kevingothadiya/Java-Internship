package com.example.customquerymethods.service.implementation;

import com.example.customquerymethods.custom.exsception.NoStudentFoundException;
import com.example.customquerymethods.dto.StudentDto;
import com.example.customquerymethods.entity.Student;
import com.example.customquerymethods.helper.MapperHelper;
import com.example.customquerymethods.repository.StudentRepo;
import com.example.customquerymethods.service.StudentService;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private MapperHelper helper;

    @Autowired
    private Faker faker;

    @Override
    public String generateRendomData() {
        for(int i=0;i<100;i++){
            Student std = new Student();
            std.setFirstName(faker.name().firstName());
            std.setLastName(faker.name().lastName());
            std.setAge(faker.number().numberBetween(1,50));
            std.setAddress(faker.address().fullAddress());
            std.setEmailId(faker.internet().emailAddress());
            std.setMobileNum(faker.phoneNumber().phoneNumber());

            studentRepo.save(std);
        }
        return "Successfully Add Data";
    }

    @Override
    public List<StudentDto> getAll() {
        if(studentRepo.findAll().isEmpty()){
            throw new NoStudentFoundException("No Student Available", HttpStatus.NOT_FOUND.value());
        }
        else {
            return helper.getListStudentDto(studentRepo.findAll());
        }
    }

    @Override
    public List<StudentDto> findStudentByFirstName(String firstName) {
        if(studentRepo.findByFirstName(firstName).isEmpty()){
            throw new NoStudentFoundException("No Student Available with Name : " + firstName,HttpStatus.NOT_FOUND.value());
        }
        else {
            return helper.getListStudentDto(studentRepo.findByFirstName(firstName));
        }
    }

    @Override
    public List<StudentDto> findStudentByFirstNameAndLastName(String firstname, String lastname) {
        if(studentRepo.findByFirstNameAndLastName(firstname,lastname).isEmpty()){
            throw new NoStudentFoundException("No Student Found with First Name " + firstname + " AND Last Name " + lastname,HttpStatus.NOT_FOUND.value());
        }
        else {
            return helper.getListStudentDto(studentRepo.findByFirstNameAndLastName(firstname,lastname));
        }
    }

    @Override
    public List<StudentDto> findStudentByFirstNameOrLastName(String firstname, String lastname) {
        if(studentRepo.findByFirstNameOrLastName(firstname,lastname).isEmpty()){
            throw new NoStudentFoundException("No Student Found with First Name " + firstname + " OR Last Name " + lastname,HttpStatus.NOT_FOUND.value());
        }
        else {
            return helper.getListStudentDto(studentRepo.findByFirstNameOrLastName(firstname,lastname));
        }
    }

    @Override
    public List<StudentDto> findStudentByFirstnameStartingWith(String firstname) {
        if(studentRepo.findByFirstNameStartingWith(firstname).isEmpty()){
            throw new NoStudentFoundException("No Student Found with First Name Start With " + firstname ,HttpStatus.NOT_FOUND.value());
        }
        else {
            return helper.getListStudentDto(studentRepo.findByFirstNameStartingWith(firstname));
        }
    }

    @Override
    public List<StudentDto> findStudentByFirstnameLike(String firstname) {
        if(studentRepo.findByFirstNameLike(firstname).isEmpty()){
            throw new NoStudentFoundException("No Student Found with First Name Like " + firstname,HttpStatus.NOT_FOUND.value());
        }
        else {
            return helper.getListStudentDto(studentRepo.findByFirstNameLike(firstname));
        }
    }

    @Override
    public List<StudentDto> findStudentByFirstnameContaining(String firstname) {
        if(studentRepo.findByFirstNameContaining(firstname).isEmpty()){
            throw new NoStudentFoundException("No Student Found with First Name Containing " + firstname,HttpStatus.NOT_FOUND.value());
        }
        else{
            return helper.getListStudentDto(studentRepo.findByFirstNameContaining(firstname));
        }
    }

    @Override
    public List<StudentDto> findStudentByAgeLessThanEqual(Integer age) {
        if (studentRepo.findByAgeLessThanEqual(age).isEmpty()){
            throw new NoStudentFoundException("No Student Found with Age Less Than Or Equal " + age,HttpStatus.NOT_FOUND.value());
        }
        else {
            return helper.getListStudentDto(studentRepo.findByAgeLessThanEqual(age));
        }
    }

    @Override
    public List<StudentDto> findStudentByAgeGreaterThan(Integer age) {
        if(studentRepo.findByAgeGreaterThan(age).isEmpty()){
            throw new NoStudentFoundException("No Student Found with Age Greater Than " + age,HttpStatus.NOT_FOUND.value());
        }
        else {
            return helper.getListStudentDto(studentRepo.findByAgeGreaterThan(age));
        }
    }

}

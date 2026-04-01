package com.example.customquerymethods.service;

import com.example.customquerymethods.dto.StudentDto;
import com.example.customquerymethods.entity.Student;

import java.util.List;

public interface StudentService {

    String generateRendomData();

    List<StudentDto> getAll();

    List<StudentDto> findStudentByFirstName(String firstName);

    List<StudentDto> findStudentByFirstNameAndLastName(String firstname, String lastname);

    List<StudentDto> findStudentByFirstNameOrLastName(String firstname, String lastname);

    List<StudentDto> findStudentByFirstnameStartingWith(String firstname);

    List<StudentDto> findStudentByFirstnameLike(String firstname);

    List<StudentDto> findStudentByFirstnameContaining(String firstname);

    List<StudentDto> findStudentByAgeLessThanEqual(Integer age);

    List<StudentDto> findStudentByAgeGreaterThan(Integer age);
}

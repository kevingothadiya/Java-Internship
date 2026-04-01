package com.example.customquerymethods.repository;

import com.example.customquerymethods.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepo extends JpaRepository<Student,Long> {

    public List<Student> findByFirstName(String name);

//    public Student findByEmailId(String email);

    //AND
    List<Student> findByFirstNameAndLastName(String firstname, String lastname);

    //OR
    List<Student> findByFirstNameOrLastName(String firstname, String lastname);

    //StartWith  -  EndWith
    List<Student> findByFirstNameStartingWith(String firstname);
//    List<Student> findByFirstNameEndingWith(String firstname);

    //Like NotLike
    List<Student> findByFirstNameLike(String firstname);
//    List<Student> findByFirstNameNotLike(String firstname);

    // CONTAINING
    List<Student> findByFirstNameContaining(String firstname);

    // LESS THAN
//    List<Student> findByAgeLessThan(Integer age);
    List<Student> findByAgeLessThanEqual(Integer age);

    // GREATER THAN
    List<Student> findByAgeGreaterThan(Integer age);
//    List<Student> findByAgeGreaterThanEqual(Integer age);
}

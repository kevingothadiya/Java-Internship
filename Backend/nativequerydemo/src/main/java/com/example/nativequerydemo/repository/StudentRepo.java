package com.example.nativequerydemo.repository;

import com.example.nativequerydemo.controller.StudentController;
import com.example.nativequerydemo.entity.Student;
import com.example.nativequerydemo.projection.FirstNameLastNameAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public interface StudentRepo extends JpaRepository<Student,Long> {

    @Query(value = "select * from student",nativeQuery = true)
    List<Student> getAllStudent();

    @Query(value = "select * from student where email_id=:email",nativeQuery = true)
    Optional<Student>  getStudentByEmailId(@Param("email") String email);

    //Object
    @Query(value = "select first_name,address from student where email_id=:email",nativeQuery = true)
    Object[] getAddressAndFirstnameByEmailID(@Param("email") String email);

    //Projection
    @Query(value = "select first_name,last_name,address from student where id=:id",nativeQuery = true)
    FirstNameLastNameAddress getFirstNameLastNameAddressByID(@Param("id") Long id);

    //CRUD

    //insert
    @Modifying
    @Transactional
    @Query(value = "insert into student (first_name,last_name,email_id,address)" +
            "VALUES (:firstname, :lastname, :email, :addr)",nativeQuery = true)
    void insertData(@Param("firstname") String firstname,@Param("lastname") String lastname,@Param("email") String email,@Param("addr") String addr);


    //update
    @Modifying
    @Transactional
    @Query(value = "UPDATE student SET first_name=:firstname,last_name=:lastname,email_id=:email,address=:addr",nativeQuery = true)
    void updateById(@Param("id") Long id,@Param("firstname") String firstname,@Param("lastname") String lastname,@Param("email") String email,@Param("addr") String addr);


    //Delete
    @Modifying
    @Transactional
    @Query(value = "DELETE from student WHERE id= :id",nativeQuery = true)
    void deleteById(@Param("id") Long id);
}

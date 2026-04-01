package com.example.onttomanyfullproject.service.implementation;

import com.example.onttomanyfullproject.custom.exception.NoStudentFoundException;
import com.example.onttomanyfullproject.dto.StudentDto;
import com.example.onttomanyfullproject.entity.Student;
import com.example.onttomanyfullproject.helper.MapperHelper;
import com.example.onttomanyfullproject.model.AuthRequest;
import com.example.onttomanyfullproject.model.AuthResponse;
import com.example.onttomanyfullproject.repository.StudentRepo;
import com.example.onttomanyfullproject.service.StudentService;
import com.example.onttomanyfullproject.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private MapperHelper mapperHelper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil util;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Override
    public String addStudent(StudentDto studentDto) {

        Student studentEntity = mapperHelper.getStudentEntity(studentDto);
        studentEntity.getAddresses().forEach(x->x.setStudent(studentEntity));
        studentEntity.setPassWord(passwordEncoder.encode(studentEntity.getPassWord()));
        studentRepo.save(studentEntity);
        return "Successfully Added";
    }

    @Override
    public AuthResponse generateToken(AuthRequest request) {
        AuthResponse authResponse = new AuthResponse();

        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassWord()));
        if(authenticate.isAuthenticated()){
            UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUserName());
            String token = util.generateToken(userDetails);

            authResponse.setToken(token);
            authResponse.setUserName(request.getUserName());
        }
        return authResponse;
    }

    @Override
    public List<StudentDto> getAllStudent() {
        if(studentRepo.findAll().isEmpty()){
            throw new NoStudentFoundException("No Student Available", HttpStatus.NOT_FOUND.value());
        }
        return mapperHelper.getListStudentDto(studentRepo.findAll());
    }

    @Override
    public StudentDto getStudentById(Long id) {
        if(studentRepo.findById(id).isPresent()){
            return mapperHelper.getStudentDto(studentRepo.findById(id).get());
        }
        else {
            throw new NoStudentFoundException("No Student Found With ID : " + id,HttpStatus.NOT_FOUND.value());
        }
    }

    @Override
    public String updateByID(Long id, StudentDto studentDto) {
        Optional<Student> byId = studentRepo.findById(id);
        if(byId.isPresent()){
            Student studentEntity = mapperHelper.getStudentEntity(studentDto);
            studentEntity.getAddresses().forEach(x->x.setStudent(studentEntity));
            studentRepo.save(studentEntity);
            return "Successfully Updated";
        }
        else {
            throw new NoStudentFoundException("No Student Found With ID : " + id,HttpStatus.NOT_FOUND.value());
        }
    }

    @Override
    public String removeStudentByEmailId(String emailId) {
        if(studentRepo.findByEmailId(emailId).isPresent()){
            studentRepo.deleteByEmailId(emailId);
            return "Successfully Deleted";
        }
        else {
            throw new NoStudentFoundException("No Student Found With Email ID : " + emailId,HttpStatus.NOT_FOUND.value());
        }
    }
}

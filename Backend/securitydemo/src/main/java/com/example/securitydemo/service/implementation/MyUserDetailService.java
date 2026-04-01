package com.example.securitydemo.service.implementation;

import com.example.securitydemo.entity.Student;
import com.example.securitydemo.repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private StudentRepo studentRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Student> byUserName = studentRepo.findByUserName(username);
        if(byUserName.isPresent()){
            Student student = byUserName.get();
            return User.builder().username(student.getUserName()).password(student.getPassword()).build();
        }
        throw new UsernameNotFoundException("No User Found");
    }
}

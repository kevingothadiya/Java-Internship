package com.example.onttomanyfullproject.service.implementation;

import com.example.onttomanyfullproject.custom.exception.UsernameNotFoundException;
import com.example.onttomanyfullproject.entity.Student;
import com.example.onttomanyfullproject.repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private StudentRepo studentRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Student> byUserName = studentRepo.findByUserName(username);
        if (byUserName.isPresent()){
            Student student = byUserName.get();

            String role = student.getRole();
            String[] split = role.split(",");

            List<SimpleGrantedAuthority> listRole = Arrays.stream(split).map(SimpleGrantedAuthority::new).toList();
            return User.builder()
                    .username(student.getUserName())
                    .password(student.getPassWord())
                    .authorities(listRole)
                    .build();
        }
        else {
            throw new UsernameNotFoundException("Invalid Username or Password");
        }
    }
}

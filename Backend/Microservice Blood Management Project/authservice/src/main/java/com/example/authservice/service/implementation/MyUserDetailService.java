package com.example.authservice.service.implementation;

import com.example.authservice.domain.Users;
import com.example.authservice.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Users> byEmail = userRepo.findByEmail(username);
        if(byEmail.isPresent()){
            Users users = byEmail.get();
            String role = users.getRole();
            String[] split = role.split(",");

            List<SimpleGrantedAuthority> simpleGrantedAuthorities = Arrays.stream(split).map(SimpleGrantedAuthority::new).toList();


            return User.builder()
                    .username(users.getEmail())
                    .password(users.getPassword())
                    .authorities(simpleGrantedAuthorities)
                    .build();
        }
        else {
            throw new UsernameNotFoundException("No User Found");
        }
    }
}

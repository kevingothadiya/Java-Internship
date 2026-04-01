package com.example.jwttoken.service.implementation;

import com.example.jwttoken.entity.Employee;
import com.example.jwttoken.repository.EmployeeRepo;
import com.example.jwttoken.service.EmployeeService;
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
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private EmployeeRepo employeeRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Employee> byUserName = employeeRepo.findByUserName(username);
        if(byUserName.isPresent()){
            Employee employee = byUserName.get();

            String role = employee.getRole();
            String[] splitRole = role.split(",");

            List<SimpleGrantedAuthority> listRole = Arrays.stream(splitRole).map(SimpleGrantedAuthority::new).toList();

//            SimpleGrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role);

            return User.builder()
                    .username(employee.getUserName())
                    .password(employee.getPassword())
                    .authorities(listRole)
                    .build();
        }
        throw new UsernameNotFoundException("No User Found");
    }
}

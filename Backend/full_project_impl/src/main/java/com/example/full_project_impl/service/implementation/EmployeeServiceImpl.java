package com.example.full_project_impl.service.implementation;

import com.example.full_project_impl.custom.exception.NoEmployeeFoundException;
import com.example.full_project_impl.dto.EmployeeDto;
import com.example.full_project_impl.entity.Employee;
import com.example.full_project_impl.helper.MapperHelper;
import com.example.full_project_impl.model.AuthRequest;
import com.example.full_project_impl.model.AuthResponse;
import com.example.full_project_impl.repository.EmployeeRepo;
import com.example.full_project_impl.service.EmployeeService;
import com.example.full_project_impl.utility.JwtUtil;
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
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private MapperHelper helper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil util;

    @Override
    public String saveEmployee(EmployeeDto employeeDto) {
        employeeDto.setPassword(passwordEncoder.encode(employeeDto.getPassword()));
        return employeeRepo.save(helper.getEmployeeEntity(employeeDto)).toString();
    }

    @Override
    public EmployeeDto getEmployeeByName(String userName) {
        Optional<Employee> byUserName = employeeRepo.findByUserName(userName);
        if(byUserName.isPresent()){
            return helper.getEmployeeDto(byUserName.get());
        }
        else {
            throw new NoEmployeeFoundException("No Employee Found With Name : " + userName, HttpStatus.NOT_FOUND.value());
        }
    }

    @Override
    public AuthResponse generateToken(AuthRequest request) {
        AuthResponse authResponse = new AuthResponse();

        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword()));

        if(authenticate.isAuthenticated()){
            UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUserName());
            String token = util.generateToken(userDetails);

            authResponse.setToken(token);
            authResponse.setUserName(request.getUserName());
        }
        return authResponse;
    }
}

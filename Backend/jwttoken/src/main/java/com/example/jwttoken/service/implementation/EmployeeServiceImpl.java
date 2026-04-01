package com.example.jwttoken.service.implementation;

import com.example.jwttoken.util.AuthUtil;
import com.example.jwttoken.dto.EmployeeDto;
import com.example.jwttoken.helper.MapperHelper;
import com.example.jwttoken.model.AuthRequest;
import com.example.jwttoken.model.AuthResponse;
import com.example.jwttoken.repository.EmployeeRepo;
import com.example.jwttoken.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

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
    private AuthUtil authUtil;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Override
    public String addEmployee(EmployeeDto employeeDto) {
        employeeDto.setPassword(passwordEncoder.encode(employeeDto.getPassword()));
        return employeeRepo.save(helper.getEmployeeEntity(employeeDto)).toString();
    }

    @Override
    public AuthResponse authenticateStudent(AuthRequest request) {

        AuthResponse authResponse = new AuthResponse();

        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword()));
        if(authenticate.isAuthenticated()){
            UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUserName());
            String token = authUtil.generateToken(userDetails);

            authResponse.setToken(token);
            authResponse.setUserName(request.getUserName());
        }
        return authResponse;
    }

    @Override
    public List<EmployeeDto> getAllEmployee() {
        return helper.getListEmployeeDto(employeeRepo.findAll());
    }
}

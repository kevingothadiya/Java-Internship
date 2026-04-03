package com.example.bloodmanagementproject.service.implementation;

import com.example.bloodmanagementproject.domain.Users;
import com.example.bloodmanagementproject.helper.MapperHelper;
import com.example.bloodmanagementproject.model.AuthRequest;
import com.example.bloodmanagementproject.model.AuthResponse;
import com.example.bloodmanagementproject.model.ForgetPassWord;
import com.example.bloodmanagementproject.proxy.UserProxy;
import com.example.bloodmanagementproject.repository.UserRepo;
import com.example.bloodmanagementproject.service.AuthService;
import com.example.bloodmanagementproject.utility.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private MapperHelper helper;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailService userDetailService;

    @Autowired
    private JwtUtil util;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public String register(UserProxy userProxy) {
        if(userRepo.findByEmail(userProxy.getEmail()).isPresent()){
            throw new RuntimeException("Email Id Already Exist");
        }
        userProxy.setPassword(passwordEncoder.encode(userProxy.getPassword()));
        userRepo.save(helper.map(userProxy, Users.class));
        return "Saved Suvvessfulyy";
    }

    @Override
    public AuthResponse login(AuthRequest authRequest) {
        AuthResponse response = new AuthResponse();

        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));

        if(authenticate.isAuthenticated()){
            UserDetails userDetails = userDetailService.loadUserByUsername(authRequest.getEmail());
            String token = util.generateToken(userDetails);

            String role = userDetails.getAuthorities()
                    .stream()
                    .findFirst()
                    .map(auth -> auth.getAuthority())
                    .orElse(null);

            response.setToken(token);
            response.setEmail(authRequest.getEmail());
            response.setRole(role);
        }
        return response;
    }

    @Override
    public String forget(ForgetPassWord forgetPassWord) {
        String email = forgetPassWord.getEmail();
        Users user = userRepo.findByEmail(email).orElseThrow(()->new RuntimeException("No Email Id Found"));
        user.setPassword(passwordEncoder.encode(forgetPassWord.getPassword()));
        userRepo.save(user);

        return "Successfully Password Forget";
    }
}

package com.example.bloodmanagementproject.controller;

import com.example.bloodmanagementproject.model.AuthRequest;
import com.example.bloodmanagementproject.model.AuthResponse;
import com.example.bloodmanagementproject.model.ForgetPassWord;
import com.example.bloodmanagementproject.proxy.UserProxy;
import com.example.bloodmanagementproject.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody UserProxy userProxy){
        return new ResponseEntity<>(authService.register(userProxy), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest){
        return new ResponseEntity<>(authService.login(authRequest),HttpStatus.OK);
    }

    @PostMapping("/forget")
    public ResponseEntity<String> forget(@RequestBody ForgetPassWord forgetPassWord){
        return new ResponseEntity<>(authService.forget(forgetPassWord),HttpStatus.OK);
    }

}

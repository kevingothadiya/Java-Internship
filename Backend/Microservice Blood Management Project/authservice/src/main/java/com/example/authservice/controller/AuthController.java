package com.example.authservice.controller;

import com.example.authservice.domain.Users;
import com.example.authservice.model.AuthRequest;
import com.example.authservice.model.AuthResponse;
import com.example.authservice.model.CheckToken;
import com.example.authservice.model.ForgetPassWord;
import com.example.authservice.proxy.TokenRoleProxy;
import com.example.authservice.proxy.UserProxy;
import com.example.authservice.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping("/validate-token")
    public ResponseEntity<Boolean> validateToken(@RequestBody TokenRoleProxy tokenRoleProxy){
        return new ResponseEntity<>(authService.ValidateToken(tokenRoleProxy),HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public Users getUser(@PathVariable Long id){
        return authService.getUser(id);
    }

    @GetMapping("/all-user")
    public List<Users> getAllUsers(){
        return authService.getAllUsers();
    }
}

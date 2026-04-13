package com.example.bloodmanagementproject.controller;

import com.example.bloodmanagementproject.model.*;
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
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest){
        try {
            return new ResponseEntity<>(authService.login(authRequest), HttpStatus.OK);
        } catch (RuntimeException e) {
            String msg = e.getMessage();
            if (msg != null && msg.startsWith("LOCKED:")) {
                return new ResponseEntity<>(msg, HttpStatus.LOCKED); // 423
            } else if (msg != null && msg.startsWith("INVALID:")) {
                return new ResponseEntity<>(msg, HttpStatus.UNAUTHORIZED); // 401
            }
            return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/forget")
    public ResponseEntity<String> forget(@RequestBody ForgetPassWord forgetPassWord){
        authService.forget(forgetPassWord);
        return new ResponseEntity<>("Password Updated Successfully",HttpStatus.OK);
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestBody ForgotPasswordRequest request){
        return new ResponseEntity<>(authService.sendResetLink(request), HttpStatus.OK);
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody ResetPasswordRequest request){
        try {
            return new ResponseEntity<>(authService.resetPassword(request), HttpStatus.OK);
        } catch (RuntimeException e) {
            if ("SAME_PASSWORD".equals(e.getMessage())) {
                return new ResponseEntity<>("Your last 3 passwords are same so please enter a unique password", HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/send-otp")
    public ResponseEntity<String> sendOtp(@RequestBody ForgotPasswordRequest request){
        return new ResponseEntity<>(authService.sendOtp(request), HttpStatus.OK);
    }

    @GetMapping("/lock-status")
    public ResponseEntity<?> getLockStatus(@RequestParam String email){
        return new ResponseEntity<>(authService.getLockStatus(email), HttpStatus.OK);
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<String> verifyOtp(@RequestBody VerifyOtpRequest request){
        return new ResponseEntity<>(authService.verifyOtp(request), HttpStatus.OK);
    }

    @PostMapping("/reset-password-otp")
    public ResponseEntity<String> resetPasswordWithOtp(@RequestBody OtpResetPasswordRequest request){
        return new ResponseEntity<>(authService.resetPasswordWithOtp(request), HttpStatus.OK);
    }
}



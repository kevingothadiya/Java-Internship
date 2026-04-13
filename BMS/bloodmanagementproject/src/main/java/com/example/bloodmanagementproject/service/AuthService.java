package com.example.bloodmanagementproject.service;

import com.example.bloodmanagementproject.model.*;
import com.example.bloodmanagementproject.proxy.UserProxy;

import com.example.bloodmanagementproject.model.LoginLockStatus;

public interface AuthService {

    String register(UserProxy userProxy);

    AuthResponse login(AuthRequest authRequest);

    String forget(ForgetPassWord forgetPassWord);

    String sendResetLink(ForgotPasswordRequest request);

    String resetPassword(ResetPasswordRequest request);

    String sendOtp(ForgotPasswordRequest request);

    String verifyOtp(VerifyOtpRequest request);

    String resetPasswordWithOtp(OtpResetPasswordRequest request);

    LoginLockStatus getLockStatus(String email);
}

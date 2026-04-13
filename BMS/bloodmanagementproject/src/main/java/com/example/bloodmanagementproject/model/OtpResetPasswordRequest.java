package com.example.bloodmanagementproject.model;

import lombok.Data;

@Data
public class OtpResetPasswordRequest {
    private String email;
    private String otp;
    private String newPassword;
}

package com.example.bloodmanagementproject.model;

import lombok.Data;

@Data
public class VerifyOtpRequest {
    private String email;
    private String otp;
}

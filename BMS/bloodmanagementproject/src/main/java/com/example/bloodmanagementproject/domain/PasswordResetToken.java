package com.example.bloodmanagementproject.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
public class PasswordResetToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String token; // stores OTP

    private String email;

    private LocalDateTime expiryTime;

    public PasswordResetToken(String token, String email) {
        this.token = token;
        this.email = email;
        this.expiryTime = LocalDateTime.now().plusMinutes(10); // 10 min OTP expiry
    }

    public boolean isExpired() {
        return LocalDateTime.now().isAfter(this.expiryTime);
    }
}

package com.example.bloodmanagementproject.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
public class PasswordHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private String hashedPassword;

    private LocalDateTime createdAt;

    public PasswordHistory(Long userId, String hashedPassword) {
        this.userId = userId;
        this.hashedPassword = hashedPassword;
        this.createdAt = LocalDateTime.now();
    }
}

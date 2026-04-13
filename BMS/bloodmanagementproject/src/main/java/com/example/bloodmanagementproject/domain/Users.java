package com.example.bloodmanagementproject.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;
    private String role;
    private String phoneNum;
    private String status;

    @Builder.Default
    @Column(columnDefinition = "int default 0")
    private Integer failedAttempts = 0;

    private LocalDateTime lockUntil;

    public boolean isLocked() {
        return lockUntil != null && LocalDateTime.now().isBefore(lockUntil);
    }

    public int getFailedAttempts() {
        return failedAttempts == null ? 0 : failedAttempts;
    }
}

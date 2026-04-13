package com.example.bloodmanagementproject.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginLockStatus {
    private boolean locked;
    private long lockRemainingSeconds; // 0 if not locked
    private int failedAttempts;
}

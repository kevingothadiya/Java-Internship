package com.example.bloodmanagementproject.repository;

import com.example.bloodmanagementproject.domain.PasswordHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PasswordHistoryRepo extends JpaRepository<PasswordHistory, Long> {

    // Returns last 3 passwords ordered by newest first
    List<PasswordHistory> findTop3ByUserIdOrderByCreatedAtDesc(Long userId);

    // Returns all for a user ordered oldest first (for deletion)
    List<PasswordHistory> findByUserIdOrderByCreatedAtAsc(Long userId);
}

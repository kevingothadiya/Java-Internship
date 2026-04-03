package com.example.bloodmanagementproject.repository;

import com.example.bloodmanagementproject.domain.DonorDetails;
import com.example.bloodmanagementproject.domain.Users;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;
import java.util.Optional;

public interface DonorDetailsRepo extends JpaRepository<DonorDetails,Long> {

    Optional<DonorDetails> findByUsers(Users users);

    Optional<List<DonorDetails>> findByBloodGrp(String bloodGrp);

    @Modifying
    @Transactional
    @Query("DELETE FROM DonorDetails d WHERE d.users.id = :userId")
    void deleteByUsersId(@Param("userId") Long userId);
}

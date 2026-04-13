package com.example.bloodmanagementproject.repository;

import com.example.bloodmanagementproject.domain.Donation;
import com.example.bloodmanagementproject.domain.DonorDetails;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DonationRepo extends JpaRepository<Donation,Long> {

    Optional<List<Donation>> findByDonorDetails(DonorDetails donorDetails);

    @Modifying
    @Transactional
    @Query("DELETE FROM Donation d WHERE d.donorDetails.users.id = :userId")
    void deleteByDonorDetailsUserId(@Param("userId") Long userId);
}

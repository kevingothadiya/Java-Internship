package com.example.bloodmanagementproject.service;

import com.example.bloodmanagementproject.domain.Users;
import com.example.bloodmanagementproject.proxy.BloodRequestProxy;
import com.example.bloodmanagementproject.proxy.BloodStockProxy;
import com.example.bloodmanagementproject.proxy.DonationProxy;
import com.example.bloodmanagementproject.proxy.UserProxy;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Modifying;

import java.util.List;

public interface AdminService {

    List<UserProxy> getUsers();

    Page<UserProxy> getUsersPaged(int page, int size);

    List<DonationProxy> getDonationDetails();

    @Transactional
    String deleteUser(Long id);

    Users updateUser(Long id, UserProxy userProxy);

    String ApproveDonationRequest(Long id);

    String RejectDonationRequest(Long id);

    String addBloodStoke(BloodStockProxy bloodStockProxy);

    String ApproveBloodRequest(Long id);

    String RejectBloodRequest(Long id);

    Page<DonationProxy> getDonationsPaged(int page, int size);

    Page<BloodRequestProxy> getBloodRequestsPaged(int page, int size);

    byte[] downloadUserExcel();

    byte[] downloadHospitalExcel();

    byte[] downloadBloodStokeExcel();

    byte[] downloadDonorDetailsExcel();
}

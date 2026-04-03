package com.example.bloodmanagementproject.service;

import com.example.bloodmanagementproject.domain.Users;
import com.example.bloodmanagementproject.proxy.BloodStockProxy;
import com.example.bloodmanagementproject.proxy.DonationProxy;
import com.example.bloodmanagementproject.proxy.UserProxy;
import jakarta.transaction.Transactional;

import java.util.List;

public interface AdminService {

    List<UserProxy> getUsers();

    List<DonationProxy> getDonationDetails();

    @Transactional
    String deleteUser(Long id);

    Users updateUser(Long id, UserProxy userProxy);

    String ApproveDonationRequest(Long id);

    String addBloodStoke(BloodStockProxy bloodStockProxy);

    String ApproveBloodRequest(Long id);

    byte[] downloadUserExcel();

    byte[] downloadHospitalExcel();

    byte[] downloadBloodStokeExcel();

    byte[] downloadDonorDetailsExcel();
}

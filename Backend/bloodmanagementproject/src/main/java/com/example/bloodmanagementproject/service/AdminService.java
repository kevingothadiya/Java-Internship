package com.example.bloodmanagementproject.service;

import com.example.bloodmanagementproject.domain.Users;
import com.example.bloodmanagementproject.proxy.BloodStockProxy;
import com.example.bloodmanagementproject.proxy.UserProxy;

import java.util.List;

public interface AdminService {

    List<UserProxy> getUsers();

    String ApproveDonationRequest(Long id);

    String addBloodStoke(BloodStockProxy bloodStockProxy);

    String ApproveBloodRequest(Long id);

    byte[] downloadUserExcel();

    byte[] downloadHospitalExcel();

    byte[] downloadBloodStokeExcel();

    byte[] downloadDonorDetailsExcel();
}

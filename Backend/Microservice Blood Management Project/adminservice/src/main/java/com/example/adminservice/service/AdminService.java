package com.example.adminservice.service;

import com.example.adminservice.domain.BloodStock;
import com.example.adminservice.domain.Users;
import com.example.adminservice.proxy.BloodStockProxy;

import java.util.List;

public interface AdminService {

    List<Users> getUsers(String token);

    String ApproveDonationRequest(Long id,String token);

    String addBloodStoke(BloodStockProxy bloodStockProxy,String token);

    String ApproveBloodRequest(Long id,String token);

    byte[] downloadUserExcel(String token);

    byte[] downloadHospitalExcel(String token);

    byte[] downloadBloodStokeExcel(String token);

    byte[] downloadDonorDetailsExcel(String token);

    BloodStock getBloodStokeByBlood(String bloodGrp);
}

package com.example.bloodmanagementproject.service;

import com.example.bloodmanagementproject.proxy.DonorDetailsProxy;
import com.example.bloodmanagementproject.proxy.HospitalProxy;
import com.example.bloodmanagementproject.proxy.UserProxy;

import java.util.List;

public interface UserService {

    List<DonorDetailsProxy> findUserByBloodGroup(String bloodGrp);

    List<DonorDetailsProxy> getAllDoners();

    List<HospitalProxy> getHospital();

}

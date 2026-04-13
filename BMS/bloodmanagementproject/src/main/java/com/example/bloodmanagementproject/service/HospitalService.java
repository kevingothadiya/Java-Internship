package com.example.bloodmanagementproject.service;

import com.example.bloodmanagementproject.model.HospitalBloodRequestHistory;
import com.example.bloodmanagementproject.model.HospitalProfileResponse;
import com.example.bloodmanagementproject.proxy.BloodRequestProxy;
import com.example.bloodmanagementproject.proxy.HospitalProxy;

import java.util.List;

public interface HospitalService {

    String addHospital(HospitalProxy hospitalProxy);

    HospitalProxy getHospitalDetails(Long id);

    String generateRequestForBlood(BloodRequestProxy bloodRequestProxy);

    List<HospitalBloodRequestHistory> getBloodRequestHistory(Long id);

    HospitalProfileResponse getHospitalByUserId(Long userId);
}

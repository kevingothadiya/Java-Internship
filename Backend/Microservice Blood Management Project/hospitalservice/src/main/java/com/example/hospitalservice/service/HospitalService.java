package com.example.hospitalservice.service;

import com.example.hospitalservice.domain.BloodRequest;
import com.example.hospitalservice.domain.Hospital;
import com.example.hospitalservice.model.HospitalBloodRequestHistory;
import com.example.hospitalservice.proxy.BloodRequestProxy;
import com.example.hospitalservice.proxy.HospitalProxy;

import java.util.List;

public interface HospitalService {

    String addHospital(HospitalProxy hospitalProxy,String token);

    HospitalProxy getHospitalDetails(Long id,String token);

    String generateRequestForBlood(BloodRequestProxy bloodRequestProxy,String token);

    List<HospitalBloodRequestHistory> getBloodRequestHistory(Long id,String token);

    BloodRequest getBloodRequestById(Long id);

    String saveBloodRequest(BloodRequest bloodRequest);

    List<Hospital> getAllHospital();
}

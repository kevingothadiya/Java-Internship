package com.example.hospitalservice.helper;

import com.example.hospitalservice.domain.BloodRequest;
import com.example.hospitalservice.domain.Hospital;
import com.example.hospitalservice.proxy.BloodRequestProxy;
import com.example.hospitalservice.proxy.HospitalProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

import java.util.List;

@Component
public class MapperHelper {

    @Autowired
    private ObjectMapper mapper;

    //Hospital

    public Hospital getHospitalDomain(HospitalProxy hospitalProxy){
        return mapper.convertValue(hospitalProxy, Hospital.class);
    }

    public HospitalProxy getHospitalProxy(Hospital hospital){
        return mapper.convertValue(hospital, HospitalProxy.class);
    }

    public List<Hospital> getListHospitalDomain(List<HospitalProxy> hospitalProxies){
        return hospitalProxies.stream().map(this::getHospitalDomain).toList();
    }

    public List<HospitalProxy> getListHospitalProxy(List<Hospital> hospitals){
        return hospitals.stream().map(this::getHospitalProxy).toList();
    }

    //BloodRequest

    public BloodRequest getBloodRequestDomain(BloodRequestProxy bloodRequestProxy){
        return mapper.convertValue(bloodRequestProxy, BloodRequest.class);
    }

    public BloodRequestProxy getBloodRequestProxy(BloodRequest bloodRequest){
        return mapper.convertValue(bloodRequest, BloodRequestProxy.class);
    }

    public List<BloodRequest> getListBloodRequestDomain(List<BloodRequestProxy> bloodRequestProxies){
        return bloodRequestProxies.stream().map(this::getBloodRequestDomain).toList();
    }

    public List<BloodRequestProxy> getListBloodRequestProxy(List<BloodRequest> bloodRequests){
        return bloodRequests.stream().map(this::getBloodRequestProxy).toList();
    }

}

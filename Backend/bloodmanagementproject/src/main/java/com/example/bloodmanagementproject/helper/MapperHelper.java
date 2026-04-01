package com.example.bloodmanagementproject.helper;

import com.example.bloodmanagementproject.domain.*;
import com.example.bloodmanagementproject.proxy.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

import java.util.List;

@Component
public class MapperHelper {

    @Autowired
    private ObjectMapper mapper;

    public <T> T map(Object source, Class<T> targetClass) {
        return mapper.convertValue(source, targetClass);
    }

    //User

    public Users getUsersDomain(UserProxy usersProxy){
        return mapper.convertValue(usersProxy, Users.class);
    }

    public UserProxy getUsersProxy(Users users){
        return mapper.convertValue(users, UserProxy.class);
    }

    public List<Users> getListUsersDomain(List<UserProxy> userProxies){
        return userProxies.stream().map(this::getUsersDomain).toList();
    }

    public List<UserProxy> getListUsersProxy(List<Users> users){
        return users.stream().map(this::getUsersProxy).toList();
    }

    //DonorDetails

    public DonorDetails getDonorDetailsDomain(DonorDetailsProxy donorDetailsProxy){
        return mapper.convertValue(donorDetailsProxy, DonorDetails.class);
    }

    public DonorDetailsProxy getDonorDetailsProxy(DonorDetails donorDetails){
        return mapper.convertValue(donorDetails, DonorDetailsProxy.class);
    }

    public List<DonorDetails> getListDonorDetailsDomain(List<DonorDetailsProxy> donorDetailsProxies){
        return donorDetailsProxies.stream().map(this::getDonorDetailsDomain).toList();
    }

    public List<DonorDetailsProxy> getListDonorDetailsProxy(List<DonorDetails> donorDetails){
        return donorDetails.stream().map(this::getDonorDetailsProxy).toList();
    }

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

    //BloodStoke

    public BloodStock getBloodStokeDomain(BloodStockProxy bloodStockProxy){
        return mapper.convertValue(bloodStockProxy, BloodStock.class);
    }

    public BloodStockProxy getBloodStockProxy(BloodStock bloodStock){
        return mapper.convertValue(bloodStock, BloodStockProxy.class);
    }

    public List<BloodStock> getListBloodStockDomain(List<BloodStockProxy> bloodStockProxies){
        return bloodStockProxies.stream().map(this::getBloodStokeDomain).toList();
    }

    public List<BloodStockProxy> getListBloodStockProxy(List<BloodStock> bloodStock){
        return bloodStock.stream().map(this::getBloodStockProxy).toList();
    }
}

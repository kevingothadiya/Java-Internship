package com.example.bloodmanagementproject.service.implementation;

import com.example.bloodmanagementproject.domain.DonorDetails;
import com.example.bloodmanagementproject.helper.MapperHelper;
import com.example.bloodmanagementproject.proxy.DonorDetailsProxy;
import com.example.bloodmanagementproject.proxy.HospitalProxy;
import com.example.bloodmanagementproject.repository.DonorDetailsRepo;
import com.example.bloodmanagementproject.repository.HospitalRepo;
import com.example.bloodmanagementproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private DonorDetailsRepo donorDetailsRepo;

    @Autowired
    private HospitalRepo hospitalRepo;

    @Autowired
    private MapperHelper helper;

    @Override
    public List<DonorDetailsProxy> findUserByBloodGroup(String bloodGrp) {
        List<DonorDetails> byBloodGrp = donorDetailsRepo.findByBloodGrp(bloodGrp).orElseThrow(()->new RuntimeException("No User Found With Blood Group : "+bloodGrp));
        return helper.getListDonorDetailsProxy(byBloodGrp);
    }

    @Override
    public List<DonorDetailsProxy> getAllDoners() {
        List<DonorDetails> all = donorDetailsRepo.findAll();
        return helper.getListDonorDetailsProxy(all);
    }

    @Override
    public List<HospitalProxy> getHospital() {
        return helper.getListHospitalProxy(hospitalRepo.findAll());
    }


}

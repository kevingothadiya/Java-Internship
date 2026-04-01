package com.example.hospitalservice.service.implementation;

import com.example.hospitalservice.domain.BloodRequest;
import com.example.hospitalservice.domain.BloodStock;
import com.example.hospitalservice.domain.Hospital;
import com.example.hospitalservice.domain.Users;
import com.example.hospitalservice.helper.MapperHelper;
import com.example.hospitalservice.model.HospitalBloodRequestHistory;
import com.example.hospitalservice.proxy.BloodRequestProxy;
import com.example.hospitalservice.proxy.HospitalProxy;
import com.example.hospitalservice.proxy.TokenRoleProxy;
import com.example.hospitalservice.repository.BloodRequestRepo;
import com.example.hospitalservice.repository.HospitalRepo;
import com.example.hospitalservice.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HospitalServiceImpl implements HospitalService {

    @Autowired
    private HospitalRepo hospitalRepo;

    @Autowired
    private MapperHelper helper;

    @Autowired
    private BloodRequestRepo bloodRequestRepo;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public String addHospital(HospitalProxy hospitalProxy,String token) {

//        TokenRoleProxy tokenRoleProxy = TokenRoleProxy.builder()
//                .role("HOSPITAL")
//                .token(token)
//                .build();
//
//        if(!restTemplate.postForObject("http://localhost:9090/gateway/auth/validate-token",tokenRoleProxy, Boolean.class)){
//            throw new RuntimeException("unauthorized");
//        }

        Users user = restTemplate.getForObject("http://localhost:9090/auth/user/" + hospitalProxy.getUserId(), Users.class);
//        Optional<Users> byId = hospitalRepo.findByUserId(hospitalProxy.getUserId());

        if(user.getRole().equalsIgnoreCase("HOSPITAL")){
            hospitalRepo.save(helper.getHospitalDomain(hospitalProxy));
            return "Hospital Create Successfully";
        }
        else {
            throw new RuntimeException("You can pass User has Not Role Hospital");
        }
    }

    @Override
    public HospitalProxy getHospitalDetails(Long id,String token) {

//        TokenRoleProxy tokenRoleProxy = TokenRoleProxy.builder()
//                .role("HOSPITAL")
//                .token(token)
//                .build();
//
//        if(!restTemplate.postForObject("http://localhost:9090/gateway/auth/validate-token",tokenRoleProxy, Boolean.class)){
//            throw new RuntimeException("unauthorized");
//        }

        Optional<Hospital> byId = hospitalRepo.findById(id);
        if(byId.isEmpty()){
            throw new RuntimeException("No Hospital Found");
        }
        else {
            Hospital hospital = byId.get();
            return helper.getHospitalProxy(hospital);
        }
    }

    @Override
    public String generateRequestForBlood(BloodRequestProxy bloodRequestProxy,String token) {

//        TokenRoleProxy tokenRoleProxy = TokenRoleProxy.builder()
//                .role("HOSPITAL")
//                .token(token)
//                .build();
//
//        if(!restTemplate.postForObject("http://localhost:9090/gateway/auth/validate-token",tokenRoleProxy, Boolean.class)){
//            throw new RuntimeException("unauthorized");
//        }


        Hospital hospital = hospitalRepo.findById(bloodRequestProxy.getHospital().getId()).orElseThrow(() -> new RuntimeException("Hospital Not Found"));
        BloodRequest bloodRequest = helper.getBloodRequestDomain(bloodRequestProxy);

        BloodStock bloodStock = restTemplate.getForObject("http://ADMINSERVICE/admin/find-by-blood/" + bloodRequestProxy.getBloodGrp(), BloodStock.class);

        System.out.println(bloodStock.getBloodGrp());
        if (bloodStock.getUnitsAvailable() >= bloodRequestProxy.getQuantity()) {
            bloodRequest.setStatus("Pending");
            bloodRequest.setRequestDate(LocalDate.now());
            bloodRequest.setHospital(hospital);
            return bloodRequestRepo.save(bloodRequest).toString();
        }
        else {
            throw new RuntimeException("No Sufficient blood available");
        }
    }

    @Override
    public List<HospitalBloodRequestHistory> getBloodRequestHistory(Long id,String token) {

//        TokenRoleProxy tokenRoleProxy = TokenRoleProxy.builder()
//                .role("HOSPITAL")
//                .token(token)
//                .build();
//
//        if(!restTemplate.postForObject("http://localhost:9090/gateway/auth/validate-token",tokenRoleProxy, Boolean.class)){
//            throw new RuntimeException("unauthorized");
//        }

        Optional<Hospital> byId = hospitalRepo.findById(id);
        if(byId.isPresent()) {
            Hospital hospital = byId.get();
            List<BloodRequest> byHospital = bloodRequestRepo.findByHospital(hospital);

            List<HospitalBloodRequestHistory> list = new ArrayList<>();

            for (BloodRequest req : byHospital){
                HospitalBloodRequestHistory bloodRequestHistory = HospitalBloodRequestHistory
                        .builder()
                        .id(req.getId())
                        .bloodGrp(req.getBloodGrp())
                        .quantity(req.getQuantity())
                        .requestDate(req.getRequestDate())
                        .status(req.getStatus())
                        .build();

                list.add(bloodRequestHistory);
            }
            return list;
        }
        else {
            throw new RuntimeException("No Hospital Found with ID : " + id);
        }
    }

    @Override
    public BloodRequest getBloodRequestById(Long id) {
        return bloodRequestRepo.findById(id).orElseThrow(()->new RuntimeException("No Blood Request Found with ID : " + id));
    }

    @Override
    public String saveBloodRequest(BloodRequest bloodRequest) {
        bloodRequestRepo.save(bloodRequest);
        return "Successfully Saved Blood Request";
    }

    @Override
    public List<Hospital> getAllHospital() {
        return hospitalRepo.findAll();
    }
}

package com.example.adminservice.service.implementation;

import com.example.adminservice.domain.*;
import com.example.adminservice.helper.ExcelHelper;
import com.example.adminservice.helper.MapperHelper;
import com.example.adminservice.proxy.BloodStockProxy;
import com.example.adminservice.proxy.TokenRoleProxy;
import com.example.adminservice.repository.BloodStockRepo;
import com.example.adminservice.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private MapperHelper helper;

    @Autowired
    private BloodStockRepo bloodStockRepo;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public List<Users> getUsers(String token) {
//
//        TokenRoleProxy tokenRoleProxy = TokenRoleProxy
//                .builder()
//                .role("ADMIN")
//                .token(token)
//                .build();
//
//        if (!restTemplate.postForObject("http://localhost:9090/gateway/auth/validate-token", tokenRoleProxy, Boolean.class)) {
//            throw new RuntimeException("unauthorized");
//        }

//        Users[] userArray = restTemplate.getForObject("http://localhost:9090/gateway/auth/all-user", Users[].class);
//        List<Users> users = Arrays.asList(userArray);
//        return users;

        ResponseEntity<List<Users>> response = restTemplate.exchange(
                "http://AUTHSERVICE/auth/all-user",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Users>>() {
                }
        );

        return response.getBody();
    }

    @Override
    public String ApproveDonationRequest(Long id, String token) {

//        TokenRoleProxy tokenRoleProxy = TokenRoleProxy
//                .builder()
//                .role("ADMIN")
//                .token(token)
//                .build();
//
//        if (!restTemplate.postForObject("http://localhost:9090/gateway/auth/validate-token", tokenRoleProxy, Boolean.class)) {
//            throw new RuntimeException("unauthorized");
//        }

        Donation donation = restTemplate.getForObject("http://DONORSERVICE/donor/get-donation-details/" + id, Donation.class);

        if (donation.getRemark().equalsIgnoreCase("Approve")) {
            throw new RuntimeException("Donation Request has already approved");
        }
        String bloodGrp = donation.getDonorDetails().getBloodGrp();
        BloodStock bloodStock = bloodStockRepo.findByBloodGrp(bloodGrp).orElseThrow(() -> new RuntimeException("No Blood Stoke Found"));
        donation.setRemark("Approve");

        bloodStock.setLastUpdated(LocalDateTime.now());
        bloodStock.setUnitsAvailable(bloodStock.getUnitsAvailable() + donation.getQuantity());

        bloodStockRepo.save(bloodStock);
        restTemplate.postForObject("http://DONORSERVICE/donor/save", donation, String.class);
        return "Successfully Approved";

    }

    @Override
    public String addBloodStoke(BloodStockProxy bloodStockProxy, String token) {

//        TokenRoleProxy tokenRoleProxy = TokenRoleProxy
//                .builder()
//                .role("ADMIN")
//                .token(token)
//                .build();
//
//        if (!restTemplate.postForObject("http://localhost:9090/gateway/auth/validate-token", tokenRoleProxy, Boolean.class)) {
//            throw new RuntimeException("unauthorized");
//        }

        BloodStock bloodStokeDomain = helper.getBloodStokeDomain(bloodStockProxy);
        bloodStokeDomain.setUnitsAvailable(0.0);
        bloodStokeDomain.setLastUpdated(LocalDateTime.now());
        return bloodStockRepo.save(bloodStokeDomain).toString();
    }

    @Override
    public String ApproveBloodRequest(Long id, String token) {

//        TokenRoleProxy tokenRoleProxy = TokenRoleProxy
//                .builder()
//                .role("ADMIN")
//                .token(token)
//                .build();
//
//        if (!restTemplate.postForObject("http://localhost:9090/gateway/auth/validate-token", tokenRoleProxy, Boolean.class)) {
//            throw new RuntimeException("unauthorized");
//        }

        BloodRequest bloodRequest = restTemplate.getForObject("http://HOSPITALSERVICE/hospital/get-blood-request/" + id, BloodRequest.class);
        String bloodGrp = bloodRequest.getBloodGrp();
        BloodStock bloodStock = bloodStockRepo.findByBloodGrp(bloodGrp).orElseThrow(() -> new RuntimeException("No Blood Available with " + bloodGrp + "Blood Group"));

        Long requiredUnits = bloodRequest.getQuantity();
        Double unitsAvailable = bloodStock.getUnitsAvailable();

        if (requiredUnits <= unitsAvailable && !bloodRequest.getStatus().equalsIgnoreCase("approve")) {
            bloodStock.setUnitsAvailable(unitsAvailable - requiredUnits);
            bloodRequest.setStatus("Approve");
            bloodStock.setLastUpdated(LocalDateTime.now());
            bloodStockRepo.save(bloodStock);
            HttpEntity<BloodRequest> entity = new HttpEntity<>(bloodRequest);

            ResponseEntity<String> response = restTemplate.exchange(
                    "http://HOSPITALSERVICE/hospital/blood-request/save",
                    HttpMethod.POST,
                    entity,
                    String.class
            );
//            restTemplate.postForObject("http://localhost:9090/gateway/hospital/blood-request/save",bloodRequest, String.class);
        } else if (requiredUnits > unitsAvailable && !bloodRequest.getStatus().equalsIgnoreCase("approve")) {
            bloodRequest.setStatus("Rejected");
            restTemplate.postForObject("http://HOSPITALSERVICE/hospital/blood-request/save", bloodRequest, String.class);
            throw new RuntimeException("No sufficient Blood available");
        }
        else if (bloodRequest.getStatus().equalsIgnoreCase("approve")) {
            throw new RuntimeException("Blood Request Has already Approved");
        }
        else {
            throw new RuntimeException("No Blood Request Found With ID : " + id);
        }
        return "Blood Request Approved";
    }

    @Override
    public byte[] downloadUserExcel(String token) {

//        TokenRoleProxy tokenRoleProxy = TokenRoleProxy
//                .builder()
//                .role("ADMIN")
//                .token(token)
//                .build();
//
//        if (!restTemplate.postForObject("http://localhost:9090/gateway/auth/validate-token", tokenRoleProxy, Boolean.class)) {
//            throw new RuntimeException("unauthorized");
//        }

        ResponseEntity<List<Users>> response = restTemplate.exchange(
                "http://AUTHSERVICE/auth/all-user",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Users>>() {
                }
        );

        List<Users> userData = response.getBody();
        return ExcelHelper.downloadUserExcel(userData);
    }

    @Override
    public byte[] downloadHospitalExcel(String token) {

//        TokenRoleProxy tokenRoleProxy = TokenRoleProxy
//                .builder()
//                .role("ADMIN")
//                .token(token)
//                .build();
//
//        if (!restTemplate.postForObject("http://localhost:9090/gateway/auth/validate-token", tokenRoleProxy, Boolean.class)) {
//            throw new RuntimeException("unauthorized");
//        }

        ResponseEntity<List<Hospital>> response = restTemplate.exchange(
                "http://HOSPITALSERVICE/hospital/get-all-hospital",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Hospital>>() {
                }
        );

        List<Hospital> hospitalData = response.getBody();
        return ExcelHelper.downloadHospitalExcel(hospitalData);
    }

    @Override
    public byte[] downloadBloodStokeExcel(String token) {

//        TokenRoleProxy tokenRoleProxy = TokenRoleProxy
//                .builder()
//                .role("ADMIN")
//                .token(token)
//                .build();
//
//        if (!restTemplate.postForObject("http://localhost:9090/gateway/auth/validate-token", tokenRoleProxy, Boolean.class)) {
//            throw new RuntimeException("unauthorized");
//        }

        List<BloodStock> bloodStocks = bloodStockRepo.findAll();
        return ExcelHelper.downloadBloodStokeDetailsExcel(bloodStocks);
    }

    @Override
    public byte[] downloadDonorDetailsExcel(String token) {

//        TokenRoleProxy tokenRoleProxy = TokenRoleProxy
//                .builder()
//                .role("ADMIN")
//                .token(token)
//                .build();
//
//        if (!restTemplate.postForObject("http://localhost:9090/gateway/auth/validate-token",tokenRoleProxy, Boolean.class)){
//            throw new RuntimeException("unauthorized");
//        }

        ResponseEntity<List<DonorDetails>> response = restTemplate.exchange(
                "http://DONORSERVICE/donor/get-all-donor-details",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<DonorDetails>>() {
                }
        );

        List<DonorDetails> donorDetails = response.getBody();
        return ExcelHelper.downloadDonorDetailsExcel(donorDetails);

    }

    @Override
    public BloodStock getBloodStokeByBlood(String bloodGrp) {
        return bloodStockRepo.findByBloodGrp(bloodGrp).orElseThrow(() -> new RuntimeException("No Blood found in Blood Stoke"));
    }
}

package com.example.bloodmanagementproject.service.implementation;

import com.example.bloodmanagementproject.domain.*;
import com.example.bloodmanagementproject.helper.ExcelHelper;
import com.example.bloodmanagementproject.helper.MapperHelper;
import com.example.bloodmanagementproject.proxy.BloodStockProxy;
import com.example.bloodmanagementproject.proxy.DonationProxy;
import com.example.bloodmanagementproject.proxy.UserProxy;
import com.example.bloodmanagementproject.repository.*;
import com.example.bloodmanagementproject.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private MapperHelper helper;

    @Autowired
    private BloodRequestRepo bloodRequestRepo;

    @Autowired
    private DonationRepo donationRepo;

    @Autowired
    private BloodStockRepo bloodStockRepo;

    @Autowired
    private HospitalRepo hospitalRepo;

    @Autowired
    private DonorDetailsRepo donorDetailsRepo;

    @Override
    public List<UserProxy> getUsers() {
        List<Users> all = userRepo.findAll();
        return all.stream().map(users -> helper.map(users,UserProxy.class)).toList();
    }

    @Override
    public List<DonationProxy> getDonationDetails() {
        List<Donation> all = donationRepo.findAll();
        return all.stream().map(donation -> helper.map(donation,DonationProxy.class)).toList();

    }

    @Override
    public String deleteUser(Long id) {
        Optional<Users> byId = userRepo.findById(id);
        if(byId.isPresent()){
            userRepo.deleteById(id);
            return "Delete Successfully";
        }
        else {
            throw new RuntimeException("No User Found with ID : " + id);
        }
    }

    @Override
    public Users updateUser(Long id, UserProxy userProxy) {
        Users existingUser = userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + id));

        existingUser.setName(userProxy.getName());
        existingUser.setEmail(userProxy.getEmail());
        existingUser.setRole(userProxy.getRole());
        existingUser.setPhoneNum(userProxy.getPhoneNum());
        existingUser.setStatus(userProxy.getStatus());

        return userRepo.save(existingUser);
    }

    @Override
    public String ApproveDonationRequest(Long id) {
        Optional<Donation> byId = donationRepo.findById(id);

        if(byId.isPresent()){
            if(byId.get().getRemark().equalsIgnoreCase("Approve")){
                throw new RuntimeException("Donation Request has already approved");
            }
            Donation donation = byId.get();
            String bloodGrp = donation.getDonorDetails().getBloodGrp();
            BloodStock bloodStock = bloodStockRepo.findByBloodGrp(bloodGrp).orElseThrow(()->new RuntimeException("No Blood Stoke Found"));
            donation.setRemark("Approve");

            bloodStock.setUnitsAvailable(bloodStock.getUnitsAvailable() + donation.getQuantity());

            bloodStockRepo.save(bloodStock);
            donationRepo.save(donation);
            return "Successfully Approved";
        }
        else {
            throw new RuntimeException("No Donation Request Found With ID : " + id);
        }

    }

    @Override
    public String addBloodStoke(BloodStockProxy bloodStockProxy) {
        BloodStock bloodStokeDomain = helper.getBloodStokeDomain(bloodStockProxy);
        bloodStokeDomain.setUnitsAvailable(0.0);
        bloodStokeDomain.setLastUpdated(LocalDateTime.now());
        return bloodStockRepo.save(bloodStokeDomain).toString();
    }

    @Override
    public String ApproveBloodRequest(Long id) {
        Optional<BloodRequest> byId = bloodRequestRepo.findById(id);
        if(byId.isPresent()){
            BloodRequest bloodRequest = byId.get();
            String bloodGrp = bloodRequest.getBloodGrp();
            BloodStock bloodStock = bloodStockRepo.findByBloodGrp(bloodGrp).orElseThrow(()->new RuntimeException("No Blood Available with " + bloodGrp + "Blood Group"));

            Long requiredUnits = bloodRequest.getQuantity();
            Double unitsAvailable = bloodStock.getUnitsAvailable();

            if(requiredUnits<unitsAvailable && !bloodRequest.getStatus().equalsIgnoreCase("approve")){
                bloodStock.setUnitsAvailable(unitsAvailable - requiredUnits);
                bloodRequest.setStatus("Approve");
                bloodStock.setLastUpdated(LocalDateTime.now());
                bloodStockRepo.save(bloodStock);
                bloodRequestRepo.save(bloodRequest);
            }
            else if(requiredUnits>unitsAvailable && !bloodRequest.getStatus().equalsIgnoreCase("approve")){
                bloodRequest.setStatus("Rejected");
                bloodRequestRepo.save(bloodRequest);
                throw new RuntimeException("No sufficient Blood available");
            }
            else if(bloodRequest.getStatus().equalsIgnoreCase("approve")){
                throw new RuntimeException("Blood Request Has already Approved");
            }
        }
        else {
            throw new RuntimeException("No Blood Request Found With ID : " + id);
        }
        return "Blood Request Approved";
    }

    @Override
    public byte[] downloadUserExcel() {
        List<Users> userData = userRepo.findAll();
        return ExcelHelper.downloadUserExcel(userData);
    }

    @Override
    public byte[] downloadHospitalExcel() {
        List<Hospital> hospitalData = hospitalRepo.findAll();
        return ExcelHelper.downloadHospitalExcel(hospitalData);
    }

    @Override
    public byte[] downloadBloodStokeExcel() {
        List<BloodStock> bloodStocks = bloodStockRepo.findAll();
        return ExcelHelper.downloadBloodStokeDetailsExcel(bloodStocks);
    }

    @Override
    public byte[] downloadDonorDetailsExcel() {
        List<DonorDetails> donorDetails = donorDetailsRepo.findAll();
        return ExcelHelper.downloadDonorDetailsExcel(donorDetails);
    }
}

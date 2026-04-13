package com.example.bloodmanagementproject.service.implementation;

import com.example.bloodmanagementproject.domain.*;
import com.example.bloodmanagementproject.helper.ExcelHelper;
import com.example.bloodmanagementproject.helper.MapperHelper;
import com.example.bloodmanagementproject.proxy.BloodRequestProxy;
import com.example.bloodmanagementproject.proxy.BloodStockProxy;
import com.example.bloodmanagementproject.proxy.DonationProxy;
import com.example.bloodmanagementproject.proxy.UserProxy;
import com.example.bloodmanagementproject.repository.*;
import com.example.bloodmanagementproject.service.AdminService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
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

    @Autowired
    private PasswordHistoryRepo passwordHistoryRepo;

    @Autowired
    private PasswordResetTokenRepo resetTokenRepo;

    @Override
    public List<UserProxy> getUsers() {
        List<Users> all = userRepo.findAll();
        return all.stream().map(users -> helper.map(users,UserProxy.class)).toList();
    }

    @Override
    public Page<UserProxy> getUsersPaged(int page, int size) {
        Page<Users> usersPage = userRepo.findAll(PageRequest.of(page, size));
        List<UserProxy> content = usersPage.getContent().stream()
                .map(u -> helper.map(u, UserProxy.class)).toList();
        return new PageImpl<>(content, usersPage.getPageable(), usersPage.getTotalElements());
    }

    @Override
    public List<DonationProxy> getDonationDetails() {
        List<Donation> all = donationRepo.findAll();
        return all.stream().map(donation -> helper.map(donation,DonationProxy.class)).toList();

    }

    @Override
    @Transactional
    public String deleteUser(Long id) {
        Users user = userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("No User Found with ID : " + id));

        // 1. Delete hospital and its blood requests
        hospitalRepo.findByUsers(user).ifPresent(hospital -> {
            bloodRequestRepo.deleteAll(bloodRequestRepo.findByHospital(hospital));
            hospitalRepo.delete(hospital);
            hospitalRepo.flush();
        });

        // 2. Delete donor details and its donations
        donorDetailsRepo.findByUsers(user).ifPresent(donor -> {
            donationRepo.findByDonorDetails(donor).ifPresent(donationRepo::deleteAll);
            donorDetailsRepo.delete(donor);
            donorDetailsRepo.flush();
        });

        // 3. Delete password history
        passwordHistoryRepo.deleteAll(
            passwordHistoryRepo.findByUserIdOrderByCreatedAtAsc(id)
        );

        // 4. Delete reset tokens
        resetTokenRepo.deleteByEmail(user.getEmail());

        // 5. Finally delete user
        userRepo.deleteById(id);
        userRepo.flush();
        return "Deleted Successfully";
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
    public String RejectDonationRequest(Long id) {
        Donation donation = donationRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("No Donation Request Found With ID : " + id));
        if (donation.getRemark().equalsIgnoreCase("Approve") || donation.getRemark().equalsIgnoreCase("Reject")) {
            throw new RuntimeException("Donation Request already processed");
        }
        donation.setRemark("Reject");
        donationRepo.save(donation);
        return "Donation Request Rejected";
    }

    @Override
    public String RejectBloodRequest(Long id) {
        BloodRequest bloodRequest = bloodRequestRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("No Blood Request Found With ID : " + id));
        if (bloodRequest.getStatus().equalsIgnoreCase("Approved") || bloodRequest.getStatus().equalsIgnoreCase("Rejected")) {
            throw new RuntimeException("Blood Request already processed");
        }
        bloodRequest.setStatus("Rejected");
        bloodRequestRepo.save(bloodRequest);
        return "Blood Request Rejected";
    }

    @Override
    public Page<DonationProxy> getDonationsPaged(int page, int size) {
        Page<Donation> donationPage = donationRepo.findAll(PageRequest.of(page, size));
        List<DonationProxy> content = donationPage.getContent().stream()
                .map(d -> helper.map(d, DonationProxy.class)).toList();
        return new PageImpl<>(content, donationPage.getPageable(), donationPage.getTotalElements());
    }

    @Override
    public Page<BloodRequestProxy> getBloodRequestsPaged(int page, int size) {
        Page<BloodRequest> bloodPage = bloodRequestRepo.findAll(PageRequest.of(page, size));
        List<BloodRequestProxy> content = bloodPage.getContent().stream()
                .map(r -> new BloodRequestProxy(
                        r.getId(),
                        r.getBloodGrp(),
                        r.getQuantity(),
                        r.getRequestDate(),
                        r.getStatus(),
                        null,
                        r.getHospital() != null ? r.getHospital().getHospitalName() : "—"
                )).toList();
        return new PageImpl<>(content, bloodPage.getPageable(), bloodPage.getTotalElements());
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
                bloodRequest.setStatus("Approved");
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

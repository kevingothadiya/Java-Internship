package com.example.bloodmanagementproject.service.implementation;

import com.example.bloodmanagementproject.custom.exception.NoDonorFoundException;
import com.example.bloodmanagementproject.domain.Donation;
import com.example.bloodmanagementproject.domain.DonorDetails;
import com.example.bloodmanagementproject.domain.Users;
import com.example.bloodmanagementproject.helper.MapperHelper;
import com.example.bloodmanagementproject.model.DonationDetailsHistory;
import com.example.bloodmanagementproject.model.DonorDetailsResponse;
import com.example.bloodmanagementproject.model.DonorProfileResponse;
import com.example.bloodmanagementproject.proxy.DonationProxy;
import com.example.bloodmanagementproject.proxy.DonorDetailsProxy;
import com.example.bloodmanagementproject.repository.DonationRepo;
import com.example.bloodmanagementproject.repository.DonorDetailsRepo;
import com.example.bloodmanagementproject.repository.UserRepo;
import com.example.bloodmanagementproject.service.DonorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DonorServiceImpl implements DonorService {

    @Autowired
    private DonorDetailsRepo donorDetailsRepo;

    @Autowired
    private MapperHelper helper;

    @Autowired
    private DonationRepo donationRepo;

    @Autowired
    private UserRepo userRepo;

    @Override
    public List<DonorDetailsResponse> getDonorDetails() {
        List<DonorDetails> donors = donorDetailsRepo.findAll();

        if (donors.isEmpty()) {
            throw new NoDonorFoundException("No Donor Details Found", HttpStatus.NOT_FOUND.value());
        }

        return donors.stream().map(donor -> {
            DonorDetailsResponse dto = new DonorDetailsResponse();
            dto.setId(donor.getId());
            dto.setBloodGrp(donor.getBloodGrp());
            dto.setAge(donor.getAge());
            dto.setGender(donor.getGender());
            dto.setCity(donor.getCity());
            dto.setLastDonationDate(donor.getLastDonationDate());
            dto.setAvailable(donor.getAvailable());
            return dto;
        }).toList();
    }

    @Override
    public DonorProfileResponse getDonorProfileByUserId(Long userId) {
        Users user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        DonorDetails donor = donorDetailsRepo.findByUsers(user)
                .orElseThrow(() -> new NoDonorFoundException("Donor not registered", 404));

        DonorProfileResponse response = new DonorProfileResponse();
        response.setId(donor.getId());
        response.setBloodGrp(donor.getBloodGrp());
        response.setAge(donor.getAge());
        response.setGender(donor.getGender());
        response.setCity(donor.getCity());
        response.setLastDonationDate(donor.getLastDonationDate());
        response.setAvailable(donor.getAvailable());

        response.setName(user.getName());
        response.setPhoneNum(user.getPhoneNum());
        response.setEmail(user.getEmail());

        return response;
    }

    @Override
    public String updateDonorDetails(DonorDetailsProxy donorDetailsProxy) {

        // Fetch the managed User entity
        Long userId = donorDetailsProxy.getUsers().getId();
        Users managedUser = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Only donors can update donor details
        if (!"DONOR".equalsIgnoreCase(managedUser.getRole())) {
            throw new RuntimeException("User does not have role DONOR");
        }

        Optional<DonorDetails> optionalDonor = donorDetailsRepo.findByUsers(managedUser);
        DonorDetails donor = helper.map(donorDetailsProxy, DonorDetails.class);
        donor.setUsers(managedUser); // set managed user entity

        DonorDetails savedDonor;
        if (optionalDonor.isPresent()) {
            donor.setId(optionalDonor.get().getId()); // keep existing ID
            savedDonor = donorDetailsRepo.save(donor);
        } else {
            savedDonor = donorDetailsRepo.save(donor); // new donor record
        }

        helper.map(savedDonor, DonorDetailsProxy.class);
        return "Donor Update Successfully";
    }

    @Override
    public String   donateBlood(DonationProxy donationProxy) {
        Long id = donationProxy.getDonorDetails().getId();
        DonorDetails donorDetails = donorDetailsRepo.findById(id).orElseThrow(() -> new NoDonorFoundException("No Donor Found",HttpStatus.NOT_FOUND.value()));

        Donation donation = new Donation();

        donation.setDonationDate(LocalDate.now());
        donation.setQuantity(donationProxy.getQuantity());
        donation.setRemark("Pending");
        donation.setDonorDetails(donorDetails);

        return donationRepo.save(donation).toString();

    }

    @Override
    public List<DonationDetailsHistory> getDonorHistory(Long id) {
        DonorDetails byId = donorDetailsRepo.findById(id).get();

        List<Donation> byDonorDetails = donationRepo.findByDonorDetails(byId).get();

        List<DonationDetailsHistory> list = new ArrayList<>();

        for (Donation don : byDonorDetails){
            DonationDetailsHistory detailsHistory = DonationDetailsHistory
                    .builder()
                    .id(don.getId())
                    .donationDate(don.getDonationDate())
                    .quantity(don.getQuantity())
                    .remark(don.getRemark())
                    .build();

            list.add(detailsHistory);
        }
        return list;

    }
}

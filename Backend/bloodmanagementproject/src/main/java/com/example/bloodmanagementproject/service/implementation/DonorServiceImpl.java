package com.example.bloodmanagementproject.service.implementation;

import com.example.bloodmanagementproject.custom.exception.NoDonorFoundException;
import com.example.bloodmanagementproject.domain.Donation;
import com.example.bloodmanagementproject.domain.DonorDetails;
import com.example.bloodmanagementproject.domain.Users;
import com.example.bloodmanagementproject.helper.MapperHelper;
import com.example.bloodmanagementproject.model.DonationDetailsHistory;
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
    public List<DonorDetailsProxy> getDonorDetails() {
        if(donorDetailsRepo.findAll().isEmpty()){
            throw new NoDonorFoundException("No Donor Details Found",HttpStatus.NOT_FOUND.value());
        }
        return donorDetailsRepo.findAll().stream()
                .map(donor->helper.map(donor, DonorDetailsProxy.class))
                .toList();
    }

    @Override
    public String updateDonorDetails(DonorDetailsProxy donorDetailsProxy) {

        Optional<Users> byId = userRepo.findById(donorDetailsProxy.getUsers().getId());

        if(byId.get().getRole().equalsIgnoreCase("DONOR")) {

            Optional<DonorDetails> byUsers = donorDetailsRepo.findByUsers(byId.get());
            DonorDetails donor = helper.map(donorDetailsProxy, DonorDetails.class);
            DonorDetails save = new DonorDetails();

            if (byUsers.isPresent()) {
                DonorDetails donorDetails = byUsers.get();
                donor.setId(donorDetails.getId());
                donor.setUsers(helper.map(donorDetailsProxy.getUsers(), Users.class));
                save = donorDetailsRepo.save(donor);
            }
            else {
                save = donorDetailsRepo.save(helper.map(donorDetailsProxy, DonorDetails.class));
            }
            return helper.map(save, DonorDetailsProxy.class).toString();
        }
        else {
            throw new RuntimeException("You can pass User has not Role Donor");
        }
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

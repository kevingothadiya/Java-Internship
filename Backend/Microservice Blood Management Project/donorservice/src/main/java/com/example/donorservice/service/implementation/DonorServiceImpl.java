package com.example.donorservice.service.implementation;

import com.example.donorservice.custom.exception.NoDonorFoundException;
import com.example.donorservice.domain.Donation;
import com.example.donorservice.domain.DonorDetails;
import com.example.donorservice.domain.Users;
import com.example.donorservice.helper.MapperHelper;
import com.example.donorservice.model.DonationDetailsHistory;
import com.example.donorservice.proxy.DonationProxy;
import com.example.donorservice.proxy.DonorDetailsProxy;
import com.example.donorservice.proxy.TokenRoleProxy;
import com.example.donorservice.repository.DonationRepo;
import com.example.donorservice.repository.DonorDetailsRepo;
import com.example.donorservice.service.DonorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
    private RestTemplate restTemplate;

    @Override
    public List<DonorDetailsProxy> getDonorDetails() {

//        TokenRoleProxy tokenRoleProxy = TokenRoleProxy.builder()
//                .role("DONOR")
//                .token(token)
//                .build();

//        if(!restTemplate.postForObject("http://localhost:9090/gateway/auth/validate-token",tokenRoleProxy,Boolean.class)){
//            throw new RuntimeException("unauthorized");
//        }

        if(donorDetailsRepo.findAll().isEmpty()){
            throw new NoDonorFoundException("No Donor Details Found",HttpStatus.NOT_FOUND.value());
        }
        return donorDetailsRepo.findAll().stream()
                .map(donor->helper.map(donor, DonorDetailsProxy.class))
                .toList();
    }

    @Override
    public String updateDonorDetails(DonorDetailsProxy donorDetailsProxy,String token) {

//        TokenRoleProxy tokenRoleProxy = TokenRoleProxy.builder()
//                .role("DONOR")
//                .token(token)
//                .build();
//
//        System.out.println(token);
//        if(!restTemplate.postForObject("http://localhost:9090/gateway/auth/validate-token",tokenRoleProxy,Boolean.class)){
//            throw new RuntimeException("unauthorized");
//        }

        Users user = restTemplate.getForObject("http://localhost:9090/auth/user/" + donorDetailsProxy.getUserId(), Users.class);

        System.out.println(user);

        if(user.getRole().equalsIgnoreCase("DONOR")) {

            Optional<DonorDetails> byUsers = donorDetailsRepo.findByUserId(donorDetailsProxy.getUserId());
            DonorDetails donor = helper.map(donorDetailsProxy, DonorDetails.class);
            DonorDetails save = new DonorDetails();

            if (byUsers.isPresent()) {
                DonorDetails donorDetails = byUsers.get();
                donor.setId(donorDetails.getId());
                save = donorDetailsRepo.save(donor);
            }
            else {
                save = donorDetailsRepo.save(donor);
            }
            return helper.map(save, DonorDetailsProxy.class).toString();
        }
        else {
            throw new RuntimeException("You can pass User has not Role Donor");
        }
    }

    @Override
    public String donateBlood(DonationProxy donationProxy,String token) {

//        TokenRoleProxy tokenRoleProxy = TokenRoleProxy.builder()
//                .role("DONOR")
//                .token(token)
//                .build();
//
//        if(!restTemplate.postForObject("http://localhost:9090/gateway/auth/validate-token",tokenRoleProxy,Boolean.class)){
//            throw new RuntimeException("unauthorized");
//        }

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
    public List<DonationDetailsHistory> getDonorHistory(Long id,String token) {

//        TokenRoleProxy tokenRoleProxy = TokenRoleProxy.builder()
//                .role("DONOR")
//                .token(token)
//                .build();
//
//        if(!restTemplate.postForObject("http://localhost:9090/gateway/auth/validate-token",tokenRoleProxy,Boolean.class)){
//            throw new RuntimeException("unauthorized");
//        }

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

    @Override
    public Donation getDonationById(Long id) {
        return donationRepo.findById(id).orElseThrow(()->new RuntimeException("No Donation Detail found with ID : " + id));
    }

    @Override
    public String SaveDonation(Donation donation) {
        donationRepo.save(donation);
        return "Saved Successfully";
    }

    @Override
    public List<DonorDetails> getDonorDetailsFa() {
        return donorDetailsRepo.findAll();
    }
}

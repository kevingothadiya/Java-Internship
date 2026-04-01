package com.example.bloodmanagementproject.service.implementation;

import com.example.bloodmanagementproject.domain.BloodRequest;
import com.example.bloodmanagementproject.domain.BloodStock;
import com.example.bloodmanagementproject.domain.Hospital;
import com.example.bloodmanagementproject.domain.Users;
import com.example.bloodmanagementproject.helper.MapperHelper;
import com.example.bloodmanagementproject.model.HospitalBloodRequestHistory;
import com.example.bloodmanagementproject.proxy.BloodRequestProxy;
import com.example.bloodmanagementproject.proxy.HospitalProxy;
import com.example.bloodmanagementproject.repository.BloodRequestRepo;
import com.example.bloodmanagementproject.repository.BloodStockRepo;
import com.example.bloodmanagementproject.repository.HospitalRepo;
import com.example.bloodmanagementproject.repository.UserRepo;
import com.example.bloodmanagementproject.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private UserRepo userRepo;

    @Autowired
    private BloodStockRepo bloodStockRepo;

    @Override
    public String addHospital(HospitalProxy hospitalProxy) {

        Optional<Users> byId = userRepo.findById(hospitalProxy.getUsers().getId());

        if(byId.get().getRole().equalsIgnoreCase("HOSPITAL")){
            hospitalProxy.setUsers(byId.get());
            return hospitalRepo.save(helper.getHospitalDomain(hospitalProxy)).toString();
        }
        else {
            throw new RuntimeException("You can pass User has Not Role Hospital");
        }
    }

    @Override
    public HospitalProxy getHospitalDetails(Long id) {
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
    public String generateRequestForBlood(BloodRequestProxy bloodRequestProxy) {
        Hospital hospital = hospitalRepo.findById(bloodRequestProxy.getHospital().getId()).orElseThrow(() -> new RuntimeException("Hospital Not Found"));
        BloodRequest bloodRequest = helper.getBloodRequestDomain(bloodRequestProxy);

        Optional<BloodStock> byBloodGrp = bloodStockRepo.findByBloodGrp(bloodRequestProxy.getBloodGrp());
        BloodStock bloodStock = byBloodGrp.get();
        if(!bloodStock.getBloodGrp().equalsIgnoreCase(bloodRequestProxy.getBloodGrp())){
            throw new RuntimeException("No Blood Found");
        }
        else if (bloodStock.getUnitsAvailable() > bloodRequestProxy.getQuantity()) {
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
    public List<HospitalBloodRequestHistory> getBloodRequestHistory(Long id) {
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
            throw new RuntimeException("No Blood Request Found");
        }
    }
}

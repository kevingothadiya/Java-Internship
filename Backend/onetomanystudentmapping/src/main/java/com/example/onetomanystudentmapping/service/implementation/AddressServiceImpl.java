package com.example.onetomanystudentmapping.service.implementation;

import com.example.onetomanystudentmapping.dto.AddressDto;
import com.example.onetomanystudentmapping.entity.Address;
import com.example.onetomanystudentmapping.repository.AddressRepository;
import com.example.onetomanystudentmapping.service.AddressService;
import com.example.onetomanystudentmapping.utility.ModelMapility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepository addressRepo;

    @Autowired
    private ModelMapility modelMapility;

    @Override
    public String saveAddData(AddressDto addressDto) {

        addressRepo.save(modelMapility.getAddressEntity(addressDto));
        return "Saved Successfully";
    }

    @Override
    public List<AddressDto> getAllAddData() {
        List<Address> all = addressRepo.findAll();
        return all.stream().map(x->modelMapility.getAddressDto(x)).toList();
    }

    @Override
    public AddressDto getAddDataById(Long id) {
        if(addressRepo.findById(id).isPresent()){
            Address address = addressRepo.findById(id).get();
            AddressDto addressDto = modelMapility.getAddressDto(address);
            return addressDto;
        }
        else {
            return new AddressDto();
        }
    }

    @Override
    public String updateAddData(AddressDto addressDto, Long id) {
        Optional<Address> byId = addressRepo.findById(id);
        if(byId.isPresent()){
            addressRepo.save(modelMapility.getAddressEntity(addressDto));
            return "Successfully Updated";
        }
        else{
            return "Not Update";
        }

    }

    @Override
    public String removeById(Long id) {
        if(addressRepo.findById(id).isPresent()){
            addressRepo.deleteById(id);
            return "Successfully Removed Data of Id : " + id;
        }
        else {
            return "No data Found Of Id : " + id;
        }
    }
}

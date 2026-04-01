package com.example.globalexceptiondemo.service.implementation;

import com.example.globalexceptiondemo.custom.exception.NoStudentFoundException;
import com.example.globalexceptiondemo.entity.Address;
import com.example.globalexceptiondemo.proxy.AddressProxy;
import com.example.globalexceptiondemo.repository.AddressRepo;
import com.example.globalexceptiondemo.service.AddressService;
import com.example.globalexceptiondemo.utility.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepo addressRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public String saveAddress(AddressProxy addressProxy) {
        addressRepo.save(modelMapper.getAddressEntity(addressProxy));
        return "Successfully Saved";
    }

    @Override
    public List<AddressProxy> getAllAddressData() {
        if(addressRepo.findAll().isEmpty()){
            throw new NoStudentFoundException("No Address Available",HttpStatus.NOT_FOUND.value());
        }
        else {
            return modelMapper.getListAddressProxy(addressRepo.findAll());
        }
    }

    @Override
    public List<AddressProxy> findAddressByCity(String city) {
        if(addressRepo.findByCity(city).isEmpty()){
            throw new NoStudentFoundException("No Student Available with City : " + city ,HttpStatus.NOT_FOUND.value());
        }
        return modelMapper.getListAddressProxy(addressRepo.findByCity(city));
    }

    @Override
    public String updateAddressData(AddressProxy addressProxy, Long id) {
        Optional<Address> byId = addressRepo.findById(id);
        if(byId.isPresent()){
            addressRepo.save(modelMapper.getAddressEntity(addressProxy));
            return "Successfully Updated";
        }
        else {
            throw new NoStudentFoundException("No Address Available with ID : " + id, HttpStatus.NOT_FOUND.value());
        }
    }

    @Override
    public String removeById(Long id) {
        Optional<Address> byId = addressRepo.findById(id);
        if(byId.isPresent()){
            byId.get().setStudent(null);
            addressRepo.delete(byId.get());
            return "Successfully Removed Data Of ID : " + id;
        }
        else {
            throw new NoStudentFoundException("No Address Available with ID : " + id,HttpStatus.NOT_FOUND.value());
        }
    }
}

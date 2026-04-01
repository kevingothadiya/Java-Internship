package com.example.onetoonebidirectional.service.implementation;

import com.example.onetoonebidirectional.domain.Address;
import com.example.onetoonebidirectional.dto.AddressDto;
import com.example.onetoonebidirectional.repository.AddressRepository;
import com.example.onetoonebidirectional.service.AddressService;
import com.example.onetoonebidirectional.utility.StudentMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepository addressRepo;

    @Autowired
    private StudentMapping studentMap;

    @Override
    public String saveAddressData(AddressDto addressDto) {
//      addressDto.getStudent().setAddress(addressDto);
        return addressRepo.save(studentMap.addDtoToaddEntity(addressDto)).toString();
//      return "Successfully Saved";

    }

    @Override
    public List<AddressDto> getAllAddressData() {
//        List<Address> all = addressRepo.findAll();
//        List<AddressDto> addressDtos = studentMap.addListEntityToAddListDto(all);
//        return addressDtos;

        return studentMap.addListEntityToAddListDto(addressRepo.findAll());
    }

    @Override
    public AddressDto getAddrssById(Long id) {
        Optional<Address> byId = addressRepo.findById(id);
        if(byId.isPresent()){
            return studentMap.addEntityToaddDto(byId.get());
        }
        else {
            return new AddressDto();
        }
    }
}

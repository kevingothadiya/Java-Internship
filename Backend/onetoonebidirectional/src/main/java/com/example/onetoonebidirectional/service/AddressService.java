package com.example.onetoonebidirectional.service;

import com.example.onetoonebidirectional.dto.AddressDto;

import java.util.List;

public interface AddressService {

    String saveAddressData(AddressDto addressDto);

    List<AddressDto> getAllAddressData();

    AddressDto getAddrssById(Long id);
}

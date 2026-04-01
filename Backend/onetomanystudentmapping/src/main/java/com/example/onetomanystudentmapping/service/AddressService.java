package com.example.onetomanystudentmapping.service;

import com.example.onetomanystudentmapping.dto.AddressDto;

import java.util.List;

public interface AddressService {

    String saveAddData(AddressDto addressDto);

    List<AddressDto> getAllAddData();

    AddressDto getAddDataById(Long id);

    String updateAddData(AddressDto addressDto,Long id);

    String removeById(Long id);
}

package com.example.globalexceptiondemo.service;

import com.example.globalexceptiondemo.proxy.AddressProxy;

import java.util.List;

public interface AddressService {

    String saveAddress(AddressProxy addressProxy);

    List<AddressProxy> getAllAddressData();

    List<AddressProxy> findAddressByCity(String city);

    String updateAddressData(AddressProxy addressProxy,Long id);

    String removeById(Long id);
}

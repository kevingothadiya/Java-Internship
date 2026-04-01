package com.example.globalexceptiondemo.repository;

import com.example.globalexceptiondemo.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepo extends JpaRepository<Address,Long> {

    public List<Address> findByCity(String city);
}

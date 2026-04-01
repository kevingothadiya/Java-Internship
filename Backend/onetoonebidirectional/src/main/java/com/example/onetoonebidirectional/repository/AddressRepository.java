package com.example.onetoonebidirectional.repository;

import com.example.onetoonebidirectional.domain.Address;
import com.example.onetoonebidirectional.dto.AddressDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address,Long> {
}

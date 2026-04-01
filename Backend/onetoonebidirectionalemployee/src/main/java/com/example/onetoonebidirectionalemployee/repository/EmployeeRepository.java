package com.example.onetoonebidirectionalemployee.repository;

import com.example.onetoonebidirectionalemployee.domain.Employee;
import com.example.onetoonebidirectionalemployee.dto.EmployeeDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    public Employee findByEmail(String email);
}

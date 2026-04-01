package com.example.onetoonebidirectionalemployee.service.implementation;

import com.example.onetoonebidirectionalemployee.customize.exception.NotEmployeeFoundException;
import com.example.onetoonebidirectionalemployee.domain.Company;
import com.example.onetoonebidirectionalemployee.domain.Employee;
import com.example.onetoonebidirectionalemployee.dto.EmployeeDto;
import com.example.onetoonebidirectionalemployee.repository.EmployeeRepository;
import com.example.onetoonebidirectionalemployee.service.EmployeeService;
import com.example.onetoonebidirectionalemployee.utility.MapperUtility;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepo;

    @Autowired
    private MapperUtility mapperUtility;

    @Override
    public String saveEmpData(EmployeeDto employeeDto) {
        return employeeRepo.save(mapperUtility.empDtoToEmpEntity(employeeDto)).toString();
    }

    @Override
    public List<EmployeeDto> getAllEmpData() {
        if(employeeRepo.findAll().isEmpty()){
            throw new NotEmployeeFoundException("No Employee Details Available", HttpStatus.NOT_FOUND.value());
        }
        else {
            return mapperUtility.empListEntityToEmpListDto(employeeRepo.findAll());
        }
    }

    @Override
    public EmployeeDto findEmpByEmail(String email) {

        return mapperUtility.empEntityToEmpDto(employeeRepo.findByEmail(email));
    }

    @Override
    public String updateEmp(EmployeeDto employeeDto, Long id) {
        Optional<Employee> byId = employeeRepo.findById(id);
        if(byId.isPresent()){
            Employee employee = byId.get();
            employee.setName(employeeDto.getName());
            employee.setAddress(employeeDto.getAddress());
            employee.setEmail(employeeDto.getEmail());
            if(employeeDto.getCompany()!=null) {

                if (employee.getCompany() == null) {
                    employee.setCompany(new Company());
                }
                employee.getCompany().setComName(employeeDto.getCompany().getComName());
                employee.getCompany().setComAddress(employeeDto.getCompany().getComAddress());

                employee.getCompany().setEmployee(employee);
            }
            employeeRepo.save(employee);
            return "Successfully Updated";
        }
        else {
            throw new NotEmployeeFoundException("No Employee found of ID : " + id,HttpStatus.NOT_FOUND.value());
        }

    }

    @Override
    public String removeById(Long id) {
        Optional<Employee> byId = employeeRepo.findById(id);
        if(byId.isPresent()){
            employeeRepo.deleteById(id);
            return "Successfully Rempoved Data Of Id : " + id;
        }
        else {
            throw new NotEmployeeFoundException("No Data Found Of Id : " + id,HttpStatus.NOT_FOUND.value());
        }
    }

    @Override
    public String removeAll() {
        if(employeeRepo.findAll().isEmpty()){
            throw new NotEmployeeFoundException("No Employee Details Available",HttpStatus.NOT_FOUND.value());
        }
        else {
            employeeRepo.deleteAll();
            return "Successfully Removed";
        }
    }
}

package com.example.onetoonebidirectionalemployee.utility;

import com.example.onetoonebidirectionalemployee.domain.Company;
import com.example.onetoonebidirectionalemployee.domain.Employee;
import com.example.onetoonebidirectionalemployee.dto.CompanyDto;
import com.example.onetoonebidirectionalemployee.dto.EmployeeDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MapperUtility {

    @Autowired
    private ModelMapper modelMapper;

    public Employee empDtoToEmpEntity(EmployeeDto employeeDto){
        return modelMapper.map(employeeDto,Employee.class);
    }

    public EmployeeDto empEntityToEmpDto(Employee employee){
        employee.getCompany().setEmployee(null);
        return modelMapper.map(employee, EmployeeDto.class);
    }

    public List<Employee> empListDtoTOEmpListEntity(List<EmployeeDto> employeeDtos){
        return employeeDtos.stream().map(this::empDtoToEmpEntity).toList();
    }

    public List<EmployeeDto> empListEntityToEmpListDto(List<Employee> employees){
        return employees.stream().map(this::empEntityToEmpDto).toList();
    }

    public Company comDtoToComEntity(CompanyDto companyDto){
        return modelMapper.map(companyDto, Company.class);
    }

    public CompanyDto comEntityToComDto(Company company){
        company.getEmployee().setCompany(null);
        return modelMapper.map(company, CompanyDto.class);
    }

    public List<Company> comListDtoToComListEntity(List<CompanyDto> companyDtos){
        return companyDtos.stream().map(this::comDtoToComEntity).toList();
    }

    public List<CompanyDto> comListEntityToComListDto(List<Company> companies){
        return companies.stream().map(this::comEntityToComDto).toList();
    }
}

package com.example.onetoonebidirectionalemployee.service.implementation;

import com.example.onetoonebidirectionalemployee.customize.exception.NotEmployeeFoundException;
import com.example.onetoonebidirectionalemployee.domain.Company;
import com.example.onetoonebidirectionalemployee.domain.Employee;
import com.example.onetoonebidirectionalemployee.dto.CompanyDto;
import com.example.onetoonebidirectionalemployee.repository.CompanyRepository;
import com.example.onetoonebidirectionalemployee.service.CompanyService;
import com.example.onetoonebidirectionalemployee.utility.MapperUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyRepository companyRepo;

    @Autowired
    private MapperUtility mapperUtility;

    @Override
    public String saveComData(CompanyDto companyDto) {
        companyDto.getEmployee().setCompany(companyDto);
        companyRepo.save(mapperUtility.comDtoToComEntity(companyDto));
        return "successfully Saved";
    }

    @Override
    public List<CompanyDto> getAllComData() {
        if(companyRepo.findAll().isEmpty()){
            throw new RuntimeException("No Company Data Available");
        }
        return mapperUtility.comListEntityToComListDto(companyRepo.findAll());
    }

    @Override
    public CompanyDto findComDataById(Long id) {
        if(companyRepo.findById(id).isPresent()){
            Company company = companyRepo.findById(id).get();
            CompanyDto companyDto = mapperUtility.comEntityToComDto(company);
            return companyDto;
        }
        else {
            throw new NotEmployeeFoundException("No Company Found of ID : " + id,HttpStatus.NOT_FOUND.value());
        }
    }

    @Override
    public String updateCom(CompanyDto companyDto, Long id) {
        Optional<Company> byId = companyRepo.findById(id);
        if(byId.isPresent()){
            Company existingCompany = byId.get();
            existingCompany.setComName(companyDto.getComName());
            existingCompany.setComAddress(companyDto.getComAddress());

            if(companyDto.getEmployee()!=null){
                if(existingCompany.getEmployee()==null){
                    existingCompany.setEmployee(new Employee());
                }
                existingCompany.getEmployee().setName(companyDto.getEmployee().getName());
                existingCompany.getEmployee().setEmail(companyDto.getEmployee().getEmail());
                existingCompany.getEmployee().setAddress(companyDto.getEmployee().getAddress());

                existingCompany.getEmployee().setCompany(existingCompany);
            }

            companyRepo.save(existingCompany);
            return "Successfully Updated";
        }
        else {
            throw new NotEmployeeFoundException("No Company Found OF ID : " + id,HttpStatus.NOT_FOUND.value());
        }
    }

    @Override
    public String removeComDataById(Long id) {
        if(companyRepo.findById(id).isPresent()){
            companyRepo.deleteById(id);
            return "Successfully Remove Data Of ID : " + id;
        }
        else {
            throw new NotEmployeeFoundException("No Data Found of ID : " + id,HttpStatus.NOT_FOUND.value());
        }
    }

    @Override
    public String removeAllCompanyData() {
        if(companyRepo.findAll().isEmpty()){
            throw new NotEmployeeFoundException("No Data Exist",HttpStatus.NOT_FOUND.value());
        }
        else {
            companyRepo.deleteAll();
            return "Removed All Data Successfully";
        }
    }
}

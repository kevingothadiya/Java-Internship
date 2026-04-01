package com.example.onetoonebidirectionalemployee.service;

import com.example.onetoonebidirectionalemployee.dto.CompanyDto;

import java.util.List;

public interface CompanyService {

    String saveComData(CompanyDto companyDto);

    List<CompanyDto> getAllComData();

    CompanyDto findComDataById(Long id);

    String updateCom(CompanyDto companyDto,Long id);

    String removeComDataById(Long id);

    String removeAllCompanyData();
}

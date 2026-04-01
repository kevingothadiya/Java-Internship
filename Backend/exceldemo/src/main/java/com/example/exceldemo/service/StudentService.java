package com.example.exceldemo.service;

import org.springframework.web.multipart.MultipartFile;

public interface StudentService {

    String uploadExcelData(MultipartFile excelFile);

    byte[] doenloadExcel();

    byte[] downloadStudentExcelFormate();
}

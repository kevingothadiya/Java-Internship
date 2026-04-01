package com.example.exceldemo.service.implementation;

import com.example.exceldemo.entity.Student;
import com.example.exceldemo.helper.ExcelHelper;
import com.example.exceldemo.repository.StudentRepo;
import com.example.exceldemo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepo studentRepo;

    @Override
    public String uploadExcelData(MultipartFile excelFile) {
        if(ExcelHelper.isInValidExcelFile(excelFile)) {
            return "Please Enter Valid Excel File";
        }
        else {
            studentRepo.saveAll(ExcelHelper.extractStudentDataFromExcelSheet(excelFile));
            return "Successfully Uploaded";
        }
    }

    @Override
    public byte[] doenloadExcel() {
        List<Student> studentData = studentRepo.findAll();
        return ExcelHelper.downloadExcelFromListOfStudent(studentData);
    }

    @Override
    public byte[] downloadStudentExcelFormate() {
        return ExcelHelper.downloadExcelFormate();
    }
}

package com.example.exceldemo.helper;

import com.example.exceldemo.entity.Student;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ExcelHelper {

    public static Boolean isInValidExcelFile(MultipartFile eFile){
        if(eFile.isEmpty()||eFile==null)
            return true;
        if(!eFile.getContentType().equalsIgnoreCase("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
            return true;
        return false;
    }


    public static List<Student> extractStudentDataFromExcelSheet(MultipartFile eFile){
        List<Student> stdList = new ArrayList<>();

        try(XSSFWorkbook workbook = new XSSFWorkbook(eFile.getInputStream())) {
//            InputStream inputStream = eFile.getInputStream();
//
//            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);

            XSSFSheet sheet = workbook.getSheetAt(0);

            int lastRowNum = sheet.getLastRowNum();

            for(int i=1;i<=lastRowNum;i++){
                Student std = new Student();

                XSSFRow row = sheet.getRow(i);

                std.setName(row.getCell(1).getStringCellValue());
                std.setEmail(row.getCell(2).getStringCellValue());
                std.setAddress(row.getCell(3).getStringCellValue());
                std.setPhoneNo(row.getCell(4).getRawValue());

                stdList.add(std);
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return stdList;
    }


    public static byte[] downloadExcelFromListOfStudent(List<Student> stdList){

        try (ByteArrayOutputStream os = new ByteArrayOutputStream();Workbook workbook = new XSSFWorkbook()){
//            ByteArrayOutputStream os = new ByteArrayOutputStream();
//
//            Workbook workbook = new XSSFWorkbook();

            Sheet sheet = workbook.createSheet("Student_data");

            String[] header = {"ID", "NAME", "EMAIL ID", "ADDRESS", "PHONE NUMBER"};

            Row headerRow = sheet.createRow(0);

            for (int i = 0; i < header.length; i++) {
                headerRow.createCell(i).setCellValue(header[i]);
            }

            int i = 1;
            for (Student std : stdList) {
                Row row = sheet.createRow(i);
                row.createCell(0).setCellValue(std.getId());
                row.createCell(1).setCellValue(std.getName());
                row.createCell(2).setCellValue(std.getEmail());
                row.createCell(3).setCellValue(std.getAddress());
                row.createCell(4).setCellValue(std.getPhoneNo());

                i++;
            }
            workbook.write(os);
            return os.toByteArray();
        }
        catch (IOException e){
            e.printStackTrace();
        }

        return null;
    }


    public static byte[] downloadExcelFormate(){
        try (ByteArrayOutputStream os = new ByteArrayOutputStream();Workbook workbook = new XSSFWorkbook()){

            Sheet sheet = workbook.createSheet("Student_data");

            String[] header = {"ID", "NAME", "EMAIL ID", "ADDRESS", "PHONE NUMBER"};

            Row headerRow = sheet.createRow(0);

            for (int i = 0; i < header.length; i++) {
                headerRow.createCell(i).setCellValue(header[i]);
            }

            workbook.write(os);
            return os.toByteArray();
        }
        catch (IOException e){
            e.printStackTrace();
        }

        return null;
    }
}

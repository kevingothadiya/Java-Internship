package com.example.adminservice.helper;

import com.example.adminservice.domain.BloodStock;
import com.example.adminservice.domain.DonorDetails;
import com.example.adminservice.domain.Hospital;
import com.example.adminservice.domain.Users;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelHelper {

    public static byte[] downloadUserExcel(List<Users> users) {

        try (ByteArrayOutputStream os = new ByteArrayOutputStream(); Workbook workbook = new XSSFWorkbook()) {


            Sheet sheet = workbook.createSheet("User_Data");

            String header[] = {"ID", "NAME", "EMAIL", "PASSWORD", "ROLE", "PHONE_NUM", "STATUS"};

            Row headerRow = sheet.createRow(0);

            for (int i = 0; i < header.length; i++) {
                headerRow.createCell(i).setCellValue(header[i]);
            }

            int i = 1;
            for (Users u : users) {
                Row row = sheet.createRow(i);
                row.createCell(0).setCellValue(u.getId());
                row.createCell(1).setCellValue(u.getName());
                row.createCell(2).setCellValue(u.getEmail());
                row.createCell(3).setCellValue(u.getPassword());
                row.createCell(4).setCellValue(u.getRole());
                row.createCell(5).setCellValue(u.getPhoneNum());
                row.createCell(6).setCellValue(u.getStatus());

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

    public static byte[] downloadHospitalExcel(List<Hospital> hospitals) {

        try (ByteArrayOutputStream os = new ByteArrayOutputStream(); Workbook workbook = new XSSFWorkbook()) {


            Sheet sheet = workbook.createSheet("Hospital_Data");

            String header[] = {"ID", "HOSPITAL_NAME", "ADDRESS" , "CONTACT_NUMBER", "LICENCE_NUMBER"};

            Row headerRow = sheet.createRow(0);

            for (int i = 0; i < header.length; i++) {
                headerRow.createCell(i).setCellValue(header[i]);
            }

            int i = 1;
            for (Hospital hos : hospitals) {
                Row row = sheet.createRow(i);
                row.createCell(0).setCellValue(hos.getId());
                row.createCell(1).setCellValue(hos.getHospitalName());
                row.createCell(2).setCellValue(hos.getAddress());
                row.createCell(3).setCellValue(hos.getContactNum());
                row.createCell(4).setCellValue(hos.getLicenceNumber());

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

    public static byte[] downloadBloodStokeDetailsExcel(List<BloodStock> bloodStocks) {

        try (ByteArrayOutputStream os = new ByteArrayOutputStream(); Workbook workbook = new XSSFWorkbook()) {


            Sheet sheet = workbook.createSheet("Blood_Stoke_Data");

            String header[] = {"ID", "BLOOD_GROUP", "UNITS_AVAILABLE" , "LAST_UPDATED"};

            Row headerRow = sheet.createRow(0);

            for (int i = 0; i < header.length; i++) {
                headerRow.createCell(i).setCellValue(header[i]);
            }

            int i = 1;
            for (BloodStock blood : bloodStocks) {
                Row row = sheet.createRow(i);
                row.createCell(0).setCellValue(blood.getId());
                row.createCell(1).setCellValue(blood.getBloodGrp());
                row.createCell(2).setCellValue(blood.getUnitsAvailable());
                row.createCell(3).setCellValue(blood.getLastUpdated());

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

    public static byte[] downloadDonorDetailsExcel(List<DonorDetails> donorDetails) {

        try (ByteArrayOutputStream os = new ByteArrayOutputStream(); Workbook workbook = new XSSFWorkbook()) {


            Sheet sheet = workbook.createSheet("Donor_Details_Data");

            String header[] = {"ID", "BLOOD_GROUP", "AGE" , "GENDER" , "CITY" , "LAST_DONATION_DATE" , "AVAILABLE"};

            Row headerRow = sheet.createRow(0);

            for (int i = 0; i < header.length; i++) {
                headerRow.createCell(i).setCellValue(header[i]);
            }

            int i = 1;
            for (DonorDetails donor : donorDetails) {
                Row row = sheet.createRow(i);
                row.createCell(0).setCellValue(donor.getId());
                row.createCell(1).setCellValue(donor.getBloodGrp());
                row.createCell(2).setCellValue(donor.getAge());
                row.createCell(3).setCellValue(donor.getGender());
                row.createCell(4).setCellValue(donor.getCity());
                row.createCell(5).setCellValue(donor.getLastDonationDate());
                row.createCell(6).setCellValue(donor.getAvailable());

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
}
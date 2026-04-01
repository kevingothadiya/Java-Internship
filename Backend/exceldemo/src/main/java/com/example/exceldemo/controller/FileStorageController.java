package com.example.exceldemo.controller;

import com.example.exceldemo.service.implementation.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import static org.springframework.web.servlet.function.RequestPredicates.contentType;


@RestController
@RequestMapping("student")
public class FileStorageController {

    @Autowired
    private StudentServiceImpl service;

    @PostMapping(value = "/upload" , consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadStudentExcelData(@RequestParam MultipartFile excelFile){
        String s = service.uploadExcelData(excelFile);
        return new ResponseEntity<>(s, HttpStatus.OK);
    }

    @GetMapping("/excel/download")
    public ResponseEntity<byte[]> downloadStudentExcel(){
        byte[] bytes = service.doenloadExcel();
        String fileName = "Student_"+ UUID.randomUUID().toString() + ".xlsx";
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment;filename="+fileName)
                .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .body(bytes);
    }

    @GetMapping("/excel/formate/download")
    public ResponseEntity<String> downloadStudentExcelFormate() throws IOException {
//        byte[] bytes = service.downloadStudentExcelFormate();
//        String fileName = "Student_"+ UUID.randomUUID().toString() + ".xlsx";
//        return ResponseEntity.ok()
//                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment;filename="+fileName)
//                .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
//                .body(bytes);
        byte[] bytes = service.downloadStudentExcelFormate();
        String folderPath = "ExcelFormate";
        File folder = new File(folderPath);
        if(!folder.exists()){
            folder.mkdirs();
        }
        String filename = "Student.xlsx";
        Path path = Paths.get(folderPath , filename);
        try{
            Files.write(path , bytes);
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return  new ResponseEntity<>("File saved at: " + path.toAbsolutePath(),
                HttpStatus.OK);


    }
}

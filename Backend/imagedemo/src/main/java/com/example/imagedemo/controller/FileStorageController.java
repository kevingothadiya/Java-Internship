package com.example.imagedemo.controller;

import com.example.imagedemo.dto.FileStorageDto;
import com.example.imagedemo.service.implementation.FileStorageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/file")
public class FileStorageController {

    @Autowired
    private FileStorageServiceImpl fileStorageService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam MultipartFile file){
        String s = fileStorageService.storeFile(file);
        return new ResponseEntity<>(s, HttpStatus.OK);
    }

    @GetMapping("/download/{dockId}")
    public ResponseEntity<?> downloadFile(@PathVariable String dockId) throws IOException {
        FileStorageDto file = fileStorageService.getFile(dockId);

        String filePath = file.getFilePath();
        Path path = Paths.get(filePath);
        byte[]  bytes = Files.readAllBytes(path);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=" + file.getFileName())
                .contentType(MediaType.parseMediaType(file.getContentType()))
                .body(bytes);
    }
}


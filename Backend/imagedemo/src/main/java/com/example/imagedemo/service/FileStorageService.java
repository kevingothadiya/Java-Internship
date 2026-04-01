package com.example.imagedemo.service;

import com.example.imagedemo.dto.FileStorageDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileStorageService {

    String storeFile(MultipartFile file) throws IOException;

    FileStorageDto getFile(String dockId);
}

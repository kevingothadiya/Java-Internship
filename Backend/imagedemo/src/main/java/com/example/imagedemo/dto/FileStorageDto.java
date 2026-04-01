package com.example.imagedemo.dto;

import lombok.Data;

@Data
public class FileStorageDto {
    private Long id;
    private String fileName;
    private String  filePath;
    private String contentType;
    private String dockId;
    private String size;
    private byte[] data;
    private Boolean isActive;
}

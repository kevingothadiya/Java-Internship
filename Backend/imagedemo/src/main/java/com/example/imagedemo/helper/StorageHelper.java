package com.example.imagedemo.helper;

import org.springframework.web.multipart.MultipartFile;

public class StorageHelper {

    public static Boolean validateFile(MultipartFile file){
        if(file==null||file.isEmpty())
            return false;
        return true;
    }
}

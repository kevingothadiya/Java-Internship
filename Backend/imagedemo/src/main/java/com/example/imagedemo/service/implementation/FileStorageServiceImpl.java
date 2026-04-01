package com.example.imagedemo.service.implementation;

import com.example.imagedemo.dto.FileStorageDto;
import com.example.imagedemo.entity.FileStorage;
import com.example.imagedemo.helper.HelperMapper;
import com.example.imagedemo.helper.StorageHelper;
import com.example.imagedemo.repository.FileStorageRepo;
import com.example.imagedemo.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Optional;
import java.util.UUID;

@Service
public class FileStorageServiceImpl implements FileStorageService {

    @Autowired
    private FileStorageRepo fileStorageRepo;

    @Autowired
    private HelperMapper mapper;

    @Value("${document.folder}")
    private String documentpath;

    @Override
    public String storeFile(MultipartFile file){
        if(!StorageHelper.validateFile(file))
            return "Please Enter Valid File";

        try{
            String fileDockID = UUID.randomUUID().toString();
            String originalFilename = file.getOriginalFilename();
            long size = file.getSize();
            double sizeInKb = size/1024.0;
            String contentType = file.getContentType();
//            byte[] bytes = file.getBytes();

            File f = new File(documentpath);

            if(!f.exists()){
                f.mkdirs();
            }

            String fullPath = documentpath + File.separator+ fileDockID+ "_" + originalFilename;

            Files.copy(file.getInputStream(), Path.of(fullPath), StandardCopyOption.REPLACE_EXISTING);

            FileStorage fileStorage = FileStorage.builder()
                    .fileName(originalFilename)
                    .contentType(contentType)
                    .size(String.valueOf(sizeInKb))
                    .filePath(fullPath)
                    .data(null)
                    .dockId(fileDockID)
                    .isActive(true)
                    .build();
            fileStorageRepo.save(fileStorage);
            return "Document has been Saved With id : " + fileDockID;
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return "Unable to upload document";
    }

    @Override
    public FileStorageDto getFile(String dockId) {
        Optional<FileStorage> byDockId = fileStorageRepo.findByDockId(dockId);

        if(byDockId.isPresent()){
            FileStorage fileStorage = byDockId.get();
            return mapper.getFileStorageDto(fileStorage);
        }
        else {
            throw new RuntimeException("No Data Found");
        }
    }
}

package com.example.imagedemo.helper;

import com.example.imagedemo.dto.FileStorageDto;
import com.example.imagedemo.entity.FileStorage;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

import javax.management.modelmbean.ModelMBean;
import java.util.List;

@Component
public class HelperMapper {

    @Autowired
    private ModelMapper mapper;

    public FileStorage getFileStorageEntity(FileStorageDto fileStorageDto){
        return mapper.map(fileStorageDto, FileStorage.class);
    }
    public FileStorageDto getFileStorageDto(FileStorage fileStorage){
        return mapper.map(fileStorage, FileStorageDto.class);
    }

}

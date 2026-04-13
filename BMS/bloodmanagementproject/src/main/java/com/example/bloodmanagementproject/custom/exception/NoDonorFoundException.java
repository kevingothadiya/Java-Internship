package com.example.bloodmanagementproject.custom.exception;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NoDonorFoundException extends RuntimeException{

    private String errMsg;
    private Integer errCode;
    private LocalDateTime dateTime;

    public NoDonorFoundException(String errMsg, Integer errCode){
        this.errMsg = errMsg;
        this.errCode = errCode;
        this.dateTime = LocalDateTime.now();
    }
}

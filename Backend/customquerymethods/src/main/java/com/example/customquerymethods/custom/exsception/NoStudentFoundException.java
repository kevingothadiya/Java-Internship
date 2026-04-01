package com.example.customquerymethods.custom.exsception;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NoStudentFoundException extends RuntimeException{
    private String errorMsg;
    private Integer errorCode;
    private LocalDateTime dateTime;

    public NoStudentFoundException(String errorMsg,Integer errorCode){
        this.errorMsg = errorMsg;
        this.errorCode = errorCode;
        this.dateTime = LocalDateTime.now();
    }
}

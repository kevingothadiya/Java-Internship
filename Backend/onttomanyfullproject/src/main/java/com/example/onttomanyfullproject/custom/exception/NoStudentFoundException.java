package com.example.onttomanyfullproject.custom.exception;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NoStudentFoundException extends RuntimeException{
    private String errMsg;
    private Integer errCode;
    private LocalDateTime dateTime;

    public NoStudentFoundException(String errMsg, Integer errCode) {
        this.errMsg = errMsg;
        this.errCode = errCode;
        this.dateTime = LocalDateTime.now();
    }
}

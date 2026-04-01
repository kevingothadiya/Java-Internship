package com.example.onetoonebidirectionalemployee.customize.exception;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NotEmployeeFoundException extends RuntimeException{
    private String errorMsg;
    private Integer errorStatus;
    private LocalDateTime dateTime;

    public NotEmployeeFoundException(String errorMsg,Integer errorStatus)
    {
        this.errorMsg = errorMsg;
        this.errorStatus = errorStatus;
        this.dateTime = LocalDateTime.now();
    }
}

package com.example.bloodmanagementproject.custom.exception;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NoSufficientBloodAvailable extends RuntimeException{
    private String errMsgBlood;
    private Integer errCodeBlood;
    private LocalDateTime dateTime;

    public NoSufficientBloodAvailable(String errMsgBlood, Integer errCodeBlood){
        this.errMsgBlood = errMsgBlood;
        this.errCodeBlood = errCodeBlood;
        this.dateTime = LocalDateTime.now();
    }
}

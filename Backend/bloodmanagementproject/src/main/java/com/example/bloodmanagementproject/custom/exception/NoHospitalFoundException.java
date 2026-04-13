package com.example.bloodmanagementproject.custom.exception;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NoHospitalFoundException extends RuntimeException{
    private String errMsgHospital;
    private Integer errCodeHospital;
    private LocalDateTime dateTime;

    public NoHospitalFoundException(String errMsgHospital, Integer errCodeHospital){
        this.errMsgHospital = errMsgHospital;
        this.errCodeHospital = errCodeHospital;
        this.dateTime = LocalDateTime.now();
    }
}

package com.example.full_project_impl.custom.exception;

import lombok.Data;

@Data
public class NoEmployeeFoundException extends RuntimeException{

    private String errMsg;
    private Integer errStatus;

    public NoEmployeeFoundException(String errMsg,Integer errStatus){
        this.errMsg = errMsg;
        this.errStatus = errStatus;
    }
}

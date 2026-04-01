package com.example.onttomanyfullproject.custom.exception;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UsernameNotFoundException extends RuntimeException{
    private String errUserMsg;
    private LocalDateTime dateTime;

    public UsernameNotFoundException(String errUserMsg){
        this.errUserMsg = errUserMsg;
        this.dateTime = LocalDateTime.now();
    }
}

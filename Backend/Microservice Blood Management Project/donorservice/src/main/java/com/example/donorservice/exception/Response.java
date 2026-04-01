package com.example.donorservice.exception;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Response {
    private String message;
    private Integer status;
    private LocalDateTime dateTime;
    private String path;
}

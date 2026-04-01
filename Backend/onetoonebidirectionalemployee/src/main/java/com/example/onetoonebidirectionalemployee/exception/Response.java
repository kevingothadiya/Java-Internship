package com.example.onetoonebidirectionalemployee.exception;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Response {
    private String msg;
    private Integer status;
    private LocalDateTime dateTime;
    private String path;
}

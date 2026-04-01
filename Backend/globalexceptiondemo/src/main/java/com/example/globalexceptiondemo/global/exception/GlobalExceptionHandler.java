package com.example.globalexceptiondemo.global.exception;

import com.example.globalexceptiondemo.custom.exception.NoStudentFoundException;
import com.example.globalexceptiondemo.exception.Response;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Response> runTimeException(RuntimeException e, HttpServletRequest req){
        Response r = new Response();
        r.setMessage(e.getMessage());
        r.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        r.setDateTime(LocalDateTime.now());
        r.setPath(req.getRequestURI());
        return new ResponseEntity<>(r,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NoStudentFoundException.class)
    public ResponseEntity<Response> noStudentFoundException(NoStudentFoundException e,HttpServletRequest req){
        Response r = new Response();
        r.setMessage(e.getErrMsg());
        r.setStatus(e.getErrCode());
        r.setDateTime(e.getDateTime());
        r.setPath(req.getRequestURI());
        return new ResponseEntity<>(r,HttpStatus.NOT_FOUND);
    }
}

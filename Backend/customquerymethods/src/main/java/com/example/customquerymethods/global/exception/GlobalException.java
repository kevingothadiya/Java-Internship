package com.example.customquerymethods.global.exception;

import com.example.customquerymethods.custom.exsception.NoStudentFoundException;
import com.example.customquerymethods.exception.Response;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Response> runTimeException(RuntimeException e, HttpServletRequest request){
        Response res = new Response();
        res.setMsg(e.getMessage());
        res.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        res.setDateTime(LocalDateTime.now());
        res.setPath(request.getRequestURI());

        return new ResponseEntity<>(res,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NoStudentFoundException.class)
    public ResponseEntity<Response> noStudentFoundException(NoStudentFoundException e,HttpServletRequest request){
        Response res = new Response();
        res.setMsg(e.getErrorMsg());
        res.setStatus(e.getErrorCode());
        res.setDateTime(e.getDateTime());
        res.setPath(request.getRequestURI());

        return new ResponseEntity<>(res,HttpStatus.NOT_FOUND);
    }
}

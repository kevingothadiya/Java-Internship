package com.example.onetoonebidirectionalemployee.global.exception;

import com.example.onetoonebidirectionalemployee.customize.exception.NotEmployeeFoundException;
import com.example.onetoonebidirectionalemployee.exception.Response;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Response> runTimeException(RuntimeException r, HttpServletRequest request){
        Response res = new Response();
        res.setMsg(r.getMessage());
        res.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        res.setDateTime(LocalDateTime.now());
        res.setPath(request.getRequestURI());

        return new ResponseEntity<>(res,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NotEmployeeFoundException.class)
    public ResponseEntity<Response> notEmployeeFoundException(NotEmployeeFoundException e,HttpServletRequest request){
        Response res = new Response();
        res.setMsg(e.getErrorMsg());
        res.setStatus(e.getErrorStatus());
        res.setDateTime(e.getDateTime());
        res.setPath(request.getRequestURI());

        return new ResponseEntity<>(res,HttpStatus.NOT_FOUND);
    }
}

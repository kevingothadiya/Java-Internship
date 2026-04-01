package com.example.onttomanyfullproject.global.exception;

import com.example.onttomanyfullproject.custom.exception.NoStudentFoundException;
import com.example.onttomanyfullproject.custom.exception.UsernameNotFoundException;
import com.example.onttomanyfullproject.exception.Response;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Response> runTimeException(RuntimeException e, HttpServletRequest request){
        Response res = new Response();
        res.setMessage(e.getMessage());
        res.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        res.setDateTime(LocalDateTime.now());
        res.setPath(request.getRequestURI());

        return new ResponseEntity<>(res,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NoStudentFoundException.class)
    public ResponseEntity<Response> noStudentFoundException(NoStudentFoundException e,HttpServletRequest request){
        Response res = new Response();
        res.setMessage(e.getErrMsg());
        res.setStatus(e.getErrCode());
        res.setDateTime(e.getDateTime());
        res.setPath(request.getRequestURI());

        return new ResponseEntity<>(res,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<Response> userNameNotFound(UsernameNotFoundException e, HttpServletRequest request){
        Response res = new Response();
        res.setMessage(e.getErrUserMsg());
        res.setStatus(HttpStatus.NOT_FOUND.value());
        res.setDateTime(e.getDateTime());
        res.setPath(request.getRequestURI());

        return new ResponseEntity<>(res,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(
            MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}

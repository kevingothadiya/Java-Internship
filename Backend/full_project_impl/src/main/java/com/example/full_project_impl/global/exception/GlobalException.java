package com.example.full_project_impl.global.exception;

import com.example.full_project_impl.custom.exception.NoEmployeeFoundException;
import com.example.full_project_impl.exception.Response;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @ExceptionHandler(NoEmployeeFoundException.class)
    public ResponseEntity<Response> noEmployeeFoundException(NoEmployeeFoundException e,HttpServletRequest request){
        Response res = new Response();
        res.setMessage(e.getErrMsg());
        res.setStatus(e.getErrStatus());
        res.setDateTime(LocalDateTime.now());
        res.setPath(request.getRequestURI());

        return new ResponseEntity<>(res,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(
            MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}

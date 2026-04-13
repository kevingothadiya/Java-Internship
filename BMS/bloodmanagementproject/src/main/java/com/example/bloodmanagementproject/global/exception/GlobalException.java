package com.example.bloodmanagementproject.global.exception;

import com.example.bloodmanagementproject.custom.exception.NoDonorFoundException;
import com.example.bloodmanagementproject.custom.exception.NoHospitalFoundException;
import com.example.bloodmanagementproject.custom.exception.NoSufficientBloodAvailable;
import com.example.bloodmanagementproject.exception.Response;
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

    @ExceptionHandler(NoDonorFoundException.class)
    public ResponseEntity<Response> noBloodFoundException(NoDonorFoundException e, HttpServletRequest request){
        Response res = new Response();
        res.setMessage(e.getErrMsg());
        res.setStatus(e.getErrCode());
        res.setDateTime(e.getDateTime());
        res.setPath(request.getRequestURI());

        return new ResponseEntity<>(res,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoHospitalFoundException.class)
    public ResponseEntity<Response> noHospitalFoundException(NoHospitalFoundException e, HttpServletRequest request){
        Response res = new Response();
        res.setMessage(e.getErrMsgHospital());
        res.setStatus(e.getErrCodeHospital());
        res.setDateTime(e.getDateTime());
        res.setPath(request.getRequestURI());

        return new ResponseEntity<>(res,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoSufficientBloodAvailable.class)
    public ResponseEntity<Response> noSufficientBloodAvailableFoundException(NoSufficientBloodAvailable e, HttpServletRequest request){
        Response res = new Response();
        res.setMessage(e.getErrMsgBlood());
        res.setStatus(e.getErrCodeBlood());
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

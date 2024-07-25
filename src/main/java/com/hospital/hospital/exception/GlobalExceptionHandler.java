package com.hospital.hospital.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public String handleResourceNotFoundException(ResourceNotFoundException ex) {
        String message = ex.getMessage();
        return message;
    }

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity handlesRunTimeException(RuntimeException exception){
        Map<String,String> body=Map.of(
                "message",exception.getMessage()
        );
        return new ResponseEntity<>(
                body, HttpStatus.NOT_FOUND
        );
    }
    @ExceptionHandler({DataIntegrityViolationException.class})
    public String  handlesDataIntegrityViolationException(DataIntegrityViolationException exception){
        String message = exception.getMessage();
        String regex="unique_\\w+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(message);
        if(matcher.find()){
            String result = matcher.group();
            String substring = result.substring("unique_".length());
            String columnsName = substring.replace("_", " ");
return columnsName+" must be unique";
        }
        return "ERROR";
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<FieldError> fieldErrors=ex.getFieldErrors();
        List<String> errors =  new ArrayList<>();
        fieldErrors.forEach(fieldError ->
        {
            String defaultMessage= fieldError.getDefaultMessage();
            errors.add(defaultMessage);
        });

       return new ResponseEntity<>(errors,headers,status);
    }


    @ExceptionHandler(NoSeatAvailableException.class)
    public ResponseEntity handleNoSeatAvailableException(NoSeatAvailableException ex){
        return new ResponseEntity(
                Map.of("message",ex.getMessage()),
                HttpStatus.OK
        );
    }
}

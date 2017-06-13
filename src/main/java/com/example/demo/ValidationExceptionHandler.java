package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
public class ValidationExceptionHandler {

    @ExceptionHandler({MethodArgumentNotValidException.class})
    ResponseEntity<String> validationException(MethodArgumentNotValidException ex) {
        return new ResponseEntity<String>(parseErrors(ex.getBindingResult().getAllErrors()), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    /**
     * @param errorsList list of validation errors produced by SpringMVC framework
     * @return human readable errors representation
     */
    private String parseErrors(List<ObjectError> errorsList) {
        StringBuilder stringBuilder = new StringBuilder();
        for (ObjectError error : errorsList) {
            if (error.getClass().equals(FieldError.class)) {
                FieldError fieldError = (FieldError) error;
                String errorMessage = fieldError.getField() + " = " + fieldError.getRejectedValue() + " failed validation \n" +
                        "reason: " + fieldError.getDefaultMessage() + "\n";
                stringBuilder.append(errorMessage);
            } else {
                stringBuilder.append(error.getCode()).append("\n");
            }
        }
        return stringBuilder.toString();
    }

}
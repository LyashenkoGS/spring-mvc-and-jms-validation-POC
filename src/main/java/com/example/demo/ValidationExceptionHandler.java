package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.LinkedList;
import java.util.List;

@ControllerAdvice
public class ValidationExceptionHandler {

    @ExceptionHandler({MethodArgumentNotValidException.class})
    ResponseEntity<List<String>> validationException(MethodArgumentNotValidException ex) {
        return new ResponseEntity<>(parseErrors(ex.getBindingResult().getAllErrors()), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    /**
     * @param errorsList list of validation errors produced by SpringMVC framework
     * @return human readable errors representation
     */
    private List<String> parseErrors(List<ObjectError> errorsList) {
        List<String> errorsMessages = new LinkedList<>();
        for (ObjectError error : errorsList) {
            //parse field error
            if (error.getClass().equals(FieldError.class)) {
                errorsMessages.add(parseFieldError(error));
            }
            //parse an object error
            else {
                errorsMessages.add(error.getCode());
            }
        }
        return errorsMessages;
    }

    private String parseFieldError(ObjectError error) {
        FieldError fieldError = (FieldError) error;
        //parse custom exception
        if (error.getDefaultMessage() == null) {
            return fieldError.getField() + " = " + fieldError.getRejectedValue()
                    + " failed validation " + "reason: " + fieldError.getCode();
        }
        //parse javax validation error
        else {
            return fieldError.getField() + " = " + fieldError.getRejectedValue()
                    + " failed validation " + "reason: " + fieldError.getDefaultMessage();
        }
    }

}
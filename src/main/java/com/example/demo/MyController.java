package com.example.demo;

import com.example.demo.dto.AnotherDTO;
import com.example.demo.dto.SomeDTO;
import com.example.demo.validator.AnotherDTOValidator;
import com.example.demo.validator.SomeDTOValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Grigoriy Lyashenko.
 */
@RestController
public class MyController {

    @Autowired
    private MyService service;

    @Autowired
    private SomeDTOValidator someDTOValidator;
    @Autowired
    private AnotherDTOValidator anotherDTOValidator;


    @PostMapping("/somedto")
    public ResponseEntity processSomeDTO(@RequestBody @Valid SomeDTO someDTO, BindingResult errors) {
        someDTOValidator.validate(someDTO, errors);
        if (errors.hasErrors()) {
            return new ResponseEntity<>(parseErrors(errors.getAllErrors()), HttpStatus.UNPROCESSABLE_ENTITY);
        } else {
            service.processSomeDTO(someDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @PostMapping("/anotherdto")
    public ResponseEntity processAnotherDTO(@RequestBody @Valid AnotherDTO anotherDTO, BindingResult errors) {
        anotherDTOValidator.validate(anotherDTO, errors);
        if (errors.hasErrors()) {
            return new ResponseEntity<>(parseErrors(errors.getAllErrors()), HttpStatus.UNPROCESSABLE_ENTITY);
        } else {
            service.processAnotherDTO(anotherDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        }

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

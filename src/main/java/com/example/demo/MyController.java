package com.example.demo;

import com.example.demo.dto.AnotherDTO;
import com.example.demo.dto.SomeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author Grigoriy Lyashenko.
 */
@RestController
public class MyController {

    @Autowired
    private MyService service;

    @Autowired
    private Validator[] validators;

    @InitBinder
    public void setUpValidators(WebDataBinder webDataBinder) {
        Class<?> requestParameterClass = webDataBinder.getTarget().getClass();
        //iterate over all validators and set suitable ones for a request
        for (Validator validator : validators) {
            if (validator.supports(requestParameterClass)) {
                webDataBinder.addValidators(validator);
            }
        }
    }

    @PostMapping("/somedto")
    public ResponseEntity processSomeDTO(@RequestBody @Valid SomeDTO someDTO, BindingResult errors) {
        if (errors.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        } else {
            service.processSomeDTO(someDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @PostMapping("/anotherdto")
    public ResponseEntity processAnotherDTO(@RequestBody @Valid AnotherDTO anotherDTO, BindingResult errors) {
        if (errors.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        } else {
            service.processAnotherDTO(anotherDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        }

    }
}

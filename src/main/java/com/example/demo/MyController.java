package com.example.demo;

import com.example.demo.dto.AnotherDTO;
import com.example.demo.dto.SomeDTO;
import com.example.demo.validator.AnotherDTOValidator;
import com.example.demo.validator.SomeDTOValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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
    private SomeDTOValidator someDTOValidator;
    @Autowired
    private AnotherDTOValidator anotherDTOValidator;


    @PostMapping("/somedto")
    public ResponseEntity processSomeDTO(@RequestBody @Valid SomeDTO someDTO, BindingResult errors) {
        someDTOValidator.validate(someDTO, errors);
        if (errors.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        } else {
            service.processSomeDTO(someDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @PostMapping("/anotherdto")
    public ResponseEntity processAnotherDTO(@RequestBody @Valid AnotherDTO anotherDTO, BindingResult errors) {
        anotherDTOValidator.validate(anotherDTO, errors);
        if (errors.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        } else {
            service.processAnotherDTO(anotherDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        }

    }
}

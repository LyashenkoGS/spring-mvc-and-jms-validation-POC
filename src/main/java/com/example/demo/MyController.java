package com.example.demo;

import com.example.demo.dto.AnotherDTO;
import com.example.demo.dto.SomeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Grigoriy Lyashenko.
 */
@RestController
public class MyController {

    @Autowired
    private MyService service;

    @PostMapping("/somedto")
    public void processSomeDTO(@RequestBody SomeDTO someDTO) {
        service.processSomeDTO(someDTO);
    }

    @PostMapping("/anotherdto")
    public void processAnotherDTO(@RequestBody AnotherDTO anotherDTO) {
        service.processAnotherDTO(anotherDTO);
    }
}

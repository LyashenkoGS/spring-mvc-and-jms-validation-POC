package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ValidationDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ValidationDemoApplication.class, args);
    }

    @GetMapping("/")
    public String get() {
        return "azaza";
    }


    @PostMapping
    public void processSomeDTO(@RequestBody SomeDTO someDTO) {
        System.out.println(someDTO.getClass().getSimpleName() + " has been processed successfully");
    }


    @PostMapping
    public void processAnotherDTO(@RequestBody AnotherDTO anotherDTO) {
        System.out.println(anotherDTO.getClass().getSimpleName() + " has been processed successfully");
    }
}

package com.example.demo;

import com.example.demo.dto.AnotherDTO;
import com.example.demo.dto.SomeDTO;
import org.springframework.stereotype.Service;

/**
 * @author Grigoriy Lyashenko.
 */
@Service
public class MyService {

    public void processSomeDTO(SomeDTO someDTO) {
        System.out.println(someDTO.getClass().getSimpleName() + " has been processed successfully");
    }

    public void processAnotherDTO(AnotherDTO anotherDTO) {
        System.out.println(anotherDTO.getClass().getSimpleName() + " has been processed successfully");
    }


}

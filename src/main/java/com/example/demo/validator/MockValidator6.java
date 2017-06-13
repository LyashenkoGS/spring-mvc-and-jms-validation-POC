package com.example.demo.validator;

import com.example.demo.dto.MockDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * @author Grigoriy Lyashenko.
 */
@Component
public class MockValidator6 implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz == MockDTO.class;
    }

    @Override
    public void validate(Object target, Errors errors) {
        System.out.println("trigger " + this.getClass());
    }
}

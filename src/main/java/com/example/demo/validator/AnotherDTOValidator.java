package com.example.demo.validator;

import com.example.demo.dto.AnotherDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.math.BigDecimal;
import java.util.Arrays;

/**
 * @author Grigoriy Lyashenko.
 */
@Component
public class AnotherDTOValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz == AnotherDTO.class;
    }

    @Override
    public void validate(Object target, Errors errors) {
        AnotherDTO dto = ((AnotherDTO) target);
        BigDecimal[] divisionWithRemainder = dto.getSomeMagicNumber().divideAndRemainder(dto.getAmount());
        //pass if there is there is reminder
        if (divisionWithRemainder[1].compareTo(BigDecimal.ZERO) != 0) {
            errors.rejectValue("someMagicNumber", "should be divided to amount without a reminder\n" +
                    "actual :" + Arrays.toString(divisionWithRemainder));
        }
    }
}

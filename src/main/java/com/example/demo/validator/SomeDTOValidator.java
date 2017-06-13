package com.example.demo.validator;

import com.example.demo.dto.SomeDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.math.BigDecimal;
import java.util.Arrays;

/**
 * @author Grigoriy Lyashenko.
 */
@Component
public class SomeDTOValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz == SomeDTO.class;
    }

    @Override
    public void validate(Object target, Errors errors) {
        SomeDTO dto = ((SomeDTO) target);
        BigDecimal[] divisionWithRemainder = dto.getNumberOfSomething().divideAndRemainder(dto.getPricePer1KgOfRaspberry());
        //pass if there is there is reminder
        if (divisionWithRemainder[1].compareTo(BigDecimal.ZERO) != 0) {
            errors.rejectValue("numberOfSomething", "should be divided to amount without a reminder\n" +
                    "actual :" + Arrays.toString(divisionWithRemainder));
        }
    }
}

package com.example.demo.validator;

import com.example.demo.dto.SomeDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.math.BigDecimal;

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
            errors.rejectValue("numberOfSomething", "expected: should be divided to pricePer1KgOfRaspberry without a reminder. " +
                    "actual :" + "remainder after division = " + divisionWithRemainder[1]);
        }
    }
}

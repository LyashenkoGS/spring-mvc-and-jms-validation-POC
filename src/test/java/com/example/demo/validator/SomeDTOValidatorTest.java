package com.example.demo.validator;

import com.example.demo.TestDtoFactory;
import com.example.demo.dto.SomeDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

/**
 * @author Grigoriy Lyashenko.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SomeDTOValidatorTest {

    private SomeDTO someDTO;

    @Autowired
    private SomeDTOValidator someDTOValidator;

    @Before
    public void setUp() {
        someDTO = TestDtoFactory.getValidSomeDto();
    }

    @Test
    public void validateValidDto() throws Exception {
        Errors errors = new BeanPropertyBindingResult(someDTO, "someDTO");
        someDTOValidator.validate(someDTO, errors);
        assertThat("No errors", errors.getAllErrors(), is(empty()));
    }

    @Test
    public void validateInvalidDto() throws Exception {
        //corrupt dto
        BigDecimal newWrongValue = someDTO.getNumberOfSomething().add(BigDecimal.ONE);
        someDTO.setNumberOfSomething(newWrongValue);
        Errors errors = new BeanPropertyBindingResult(someDTO, "someDTO");
        //perform validation
        someDTOValidator.validate(someDTO, errors);
        assertThat("present errors", errors.getAllErrors(), is(not(empty())));
    }


}
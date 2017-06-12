package com.example.demo.validator;

import com.example.demo.TestDtoFactory;
import com.example.demo.dto.AnotherDTO;
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
public class AnotherDTOValidatorTest {

    private AnotherDTO dto;

    @Autowired
    private AnotherDTOValidator anotherDTOValidator;

    @Before
    public void setUp() {
        dto = TestDtoFactory.getValidAnotherDto();
    }

    @Test
    public void validateValidDto() throws Exception {
        Errors errors = new BeanPropertyBindingResult(dto, "dto");
        anotherDTOValidator.validate(dto, errors);
        assertThat("No errors", errors.getAllErrors(), is(empty()));
    }

    @Test
    public void validateInvalidDto() throws Exception {
        //corrupt dto
        BigDecimal newWrongValue = dto.getSomeMagicNumber().add(BigDecimal.ONE);
        dto.setSomeMagicNumber(newWrongValue);
        Errors errors = new BeanPropertyBindingResult(dto, "dto");
        //perform validation
        anotherDTOValidator.validate(dto, errors);
        assertThat("present errors", errors.getAllErrors(), is(not(empty())));
    }


}
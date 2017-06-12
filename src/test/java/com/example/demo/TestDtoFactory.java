package com.example.demo;

import com.example.demo.dto.AnotherDTO;
import com.example.demo.dto.SomeDTO;

import java.math.BigDecimal;

/**
 * Provides dto instances for tests
 *
 * @author Grigoriy Lyashenko.
 */
public abstract class TestDtoFactory {

    public static SomeDTO getValidSomeDto() {
        SomeDTO someDTO = new SomeDTO();
        someDTO.setId(1);
        someDTO.setName("name");
        someDTO.setNumberOfSomething(BigDecimal.valueOf(20L));
        someDTO.setPricePer1KgOfRaspberry(BigDecimal.TEN);
        return someDTO;
    }

    public static AnotherDTO getValidAnotherDto() {
        AnotherDTO dto = new AnotherDTO();
        dto.setId(1);
        dto.setSomeMagicNumber(BigDecimal.valueOf(20L));
        dto.setAmount(BigDecimal.TEN);
        return dto;
    }

}

package com.example.demo.dto;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * Represents an HTTP request payload
 *
 * @author Grigoriy Lyashenko (Grog).
 */
public class SomeDTO {

    @NotNull
    @Min(0)
    private Integer id;
    @NotBlank
    private String name;
    @NotNull
    @DecimalMin("0.00")
    private BigDecimal numberOfSomething;
    @NotNull
    @DecimalMin(("0.00"))
    private BigDecimal pricePer1KgOfRaspberry;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getNumberOfSomething() {
        return numberOfSomething;
    }

    public void setNumberOfSomething(BigDecimal numberOfSomething) {
        this.numberOfSomething = numberOfSomething;
    }

    public BigDecimal getPricePer1KgOfRaspberry() {
        return pricePer1KgOfRaspberry;
    }

    public void setPricePer1KgOfRaspberry(BigDecimal pricePer1KgOfRaspberry) {
        this.pricePer1KgOfRaspberry = pricePer1KgOfRaspberry;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SomeDTO)) return false;
        SomeDTO someDTO = (SomeDTO) o;
        return Objects.equals(id, someDTO.id) &&
                Objects.equals(name, someDTO.name) &&
                Objects.equals(numberOfSomething, someDTO.numberOfSomething) &&
                Objects.equals(pricePer1KgOfRaspberry, someDTO.pricePer1KgOfRaspberry);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, numberOfSomething, pricePer1KgOfRaspberry);
    }
}

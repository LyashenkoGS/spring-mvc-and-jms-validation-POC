package com.example.demo.dto;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * Represents a HTTP request payload
 *
 * @author Grigoriy Lyashenko.
 */
public class AnotherDTO {

    @NotNull
    @Min(0)
    private Integer id;
    @NotNull
    @DecimalMin("0.00")
    private BigDecimal amount;
    @NotNull
    @DecimalMin("0.00")
    private BigDecimal someMagicNumber;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getSomeMagicNumber() {
        return someMagicNumber;
    }

    public void setSomeMagicNumber(BigDecimal someMagicNumber) {
        this.someMagicNumber = someMagicNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AnotherDTO)) return false;
        AnotherDTO that = (AnotherDTO) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(amount, that.amount) &&
                Objects.equals(someMagicNumber, that.someMagicNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount, someMagicNumber);
    }
}

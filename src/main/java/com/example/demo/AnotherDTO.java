package com.example.demo;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Represents a HTTP request payload
 *
 * @author Grigoriy Lyashenko.
 */
public class AnotherDTO {

    @NotNull
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
}

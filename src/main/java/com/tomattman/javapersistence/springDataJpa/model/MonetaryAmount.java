package com.tomattman.javapersistence.springDataJpa.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Currency;

@AllArgsConstructor
@Data
public class MonetaryAmount implements Serializable {
    private final BigDecimal amount;
    private final Currency currency;

    public static MonetaryAmount fromString(String amount) {
        String[] split = amount.split(" ");
        return new MonetaryAmount(new BigDecimal(split[0]), Currency.getInstance(split[1]));
    }
}

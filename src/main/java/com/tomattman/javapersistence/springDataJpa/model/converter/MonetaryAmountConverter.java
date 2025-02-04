package com.tomattman.javapersistence.springDataJpa.model.converter;

import com.tomattman.javapersistence.springDataJpa.model.MonetaryAmount;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class MonetaryAmountConverter implements AttributeConverter<MonetaryAmount, String> {

    @Override
    public String convertToDatabaseColumn(MonetaryAmount attribute) {
        return attribute.toString();
    }

    @Override
    public MonetaryAmount convertToEntityAttribute(String dbData) {
        return MonetaryAmount.fromString(dbData);
    }
}

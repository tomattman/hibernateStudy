package com.tomattman.javapersistence.springDataJpa.model.converter;

import com.tomattman.javapersistence.springDataJpa.model.zipcode.GermanZipCode;
import com.tomattman.javapersistence.springDataJpa.model.zipcode.SwissZipCode;
import com.tomattman.javapersistence.springDataJpa.model.zipcode.ZipCode;
import jakarta.persistence.AttributeConverter;

public class ZipCodeConverter implements AttributeConverter<ZipCode, String> {
    @Override
    public String convertToDatabaseColumn(ZipCode attribute) {
        return attribute.getValue();
    }

    @Override
    public ZipCode convertToEntityAttribute(String dbData) {
        if(dbData.length() == 5) {
            return new GermanZipCode(dbData);
        } else if (dbData.length() == 4) {
            return new SwissZipCode(dbData);
        }
        throw new IllegalArgumentException("Invalid zip code");
    }
}

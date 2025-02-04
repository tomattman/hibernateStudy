package com.tomattman.javapersistence.springDataJpa.model;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    @Column(nullable = false)
    private String street;
    @AttributeOverride(
            name = "name",
            column = @Column(name = "city", nullable = false)
    )
    private City city;

}

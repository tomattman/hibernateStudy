package com.tomattman.javapersistence.springDataJpa.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class City {
    @Column(nullable = false, length = 5)
    private String zipcode;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String country;
}

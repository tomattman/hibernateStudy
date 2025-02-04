package com.tomattman.javapersistence.springDataJpa.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import org.hibernate.annotations.Immutable;

@Entity
@Immutable
public class Bid {
    @Id
    @GeneratedValue
    @Getter
    private long id;

}

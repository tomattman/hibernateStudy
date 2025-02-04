package com.tomattman.javapersistence.springDataJpa.model;

import com.tomattman.javapersistence.springDataJpa.model.converter.ZipCodeConverter;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue
    private Long id;

    private String username;

    private LocalDate registrationDate;

    private String email;

    private int level;

    private boolean active;

    @Convert(
            converter = ZipCodeConverter.class,
            attributeName = "city.zipcode"
    )
    private Address homeAddress;

    /*@Embedded
    @AttributeOverride(name = "street",
        column = @Column(name = "billing_street")
    )
    @AttributeOverride(name = "zipcode",
            column = @Column(name = "billing_zipcode")
    )
    @AttributeOverride(name = "city",
            column = @Column(name = "billing_city")
    )
    private Address billingAddress;*/

    public User(String username, LocalDate registrationDate) {
        this.username = username;
        this.registrationDate = registrationDate;
    }

    public User(String username) {
        this.username = username;
    }
}

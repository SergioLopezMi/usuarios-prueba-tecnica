package com.slopezmill.codechallenge.jpa;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;


@Setter
@Getter
@EqualsAndHashCode

@Entity
@Table(name = "ADDRESS")
public class AddressJpa implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "STREET", nullable = false)
    private String street;

    @Column(name = "STATE", nullable = false)
    private String state;

    @Column(name = "CITY", nullable = false)
    private String city;

    @Column(name = "COUNTRY", nullable = false)
    private String country;

    @Column(name = "ZIP_CODE", nullable = false)
    private String zipCode;

}

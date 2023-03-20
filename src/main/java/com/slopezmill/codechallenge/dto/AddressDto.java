package com.slopezmill.codechallenge.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
public class AddressDto implements Serializable {

    public static final long serialVersionUID = 1L;
    private Integer id;

    private String street;

    private String state;

    private String city;

    private String country;

    private String zipCode;
}

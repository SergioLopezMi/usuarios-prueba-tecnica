package com.slopezmill.codechallenge.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
public class UserDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String name;

    private String email;

    private LocalDateTime birthDate;

    private AddressDto address;
}

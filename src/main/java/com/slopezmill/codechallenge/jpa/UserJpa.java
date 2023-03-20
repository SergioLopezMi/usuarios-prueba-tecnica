package com.slopezmill.codechallenge.jpa;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;


@Setter
@Getter
@EqualsAndHashCode

@Entity
@Table(name = "USER")
public class UserJpa implements Serializable {

    //private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "EMAIL", nullable = false)
    private String email;

    @Column(name = "BIRTH_DATE", nullable = false)
    private LocalDateTime birthDate;

    @ManyToOne
    @JoinColumn(name = "ADDRESS", referencedColumnName = "ID", nullable = false)
    private AddressJpa address;

}

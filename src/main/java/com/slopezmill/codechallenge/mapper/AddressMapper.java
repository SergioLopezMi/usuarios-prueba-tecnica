package com.slopezmill.codechallenge.mapper;

import com.slopezmill.codechallenge.dto.AddressDto;
import com.slopezmill.codechallenge.jpa.AddressJpa;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    @Named("toAddressDto")
    AddressDto toAddressDto(AddressJpa addressJpa);

    @Named("toAddressJpa")
    AddressJpa toAddressJpa(AddressDto addressDto);
}

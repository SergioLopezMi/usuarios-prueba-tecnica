package com.slopezmill.codechallenge.mapper;

import com.slopezmill.codechallenge.dto.UserDto;
import com.slopezmill.codechallenge.jpa.UserJpa;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = AddressMapper.class)
public interface UserMapper {

    @Mapping(target = "address", qualifiedByName = "toAddressDto")
    UserDto toUserDto(UserJpa userJpa);

    @Mapping(target = "address", qualifiedByName = "toAddressJpa")
    UserJpa toUserJpa(UserDto userDto);

}

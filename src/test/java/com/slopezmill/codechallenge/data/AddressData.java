package com.slopezmill.codechallenge.data;

import com.slopezmill.codechallenge.dto.AddressDto;
import com.slopezmill.codechallenge.jpa.AddressJpa;

public class AddressData {

    public AddressDto getAddressDto(Integer id) {
        AddressDto addressDto = new AddressDto();
        addressDto.setId(id);
        addressDto.setStreet("Street Test");
        addressDto.setState("State Test");
        addressDto.setCity("City Test");
        addressDto.setCountry("Country Test");
        addressDto.setZipCode("1234");
        return addressDto;
    }

    public AddressJpa getAddressJpa(Integer id) {
        AddressJpa addressJpa = new AddressJpa();
        addressJpa.setId(id);
        addressJpa.setStreet("Street Test");
        addressJpa.setState("State Test");
        addressJpa.setCity("City Test");
        addressJpa.setCountry("Country Test");
        addressJpa.setZipCode("1234");
        return addressJpa;
    }
}

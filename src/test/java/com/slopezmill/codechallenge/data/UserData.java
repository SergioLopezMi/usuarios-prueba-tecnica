package com.slopezmill.codechallenge.data;

import com.slopezmill.codechallenge.dto.UserDto;
import com.slopezmill.codechallenge.jpa.UserJpa;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class UserData {

    public UserDto getUserDto(Integer id) {
        AddressData addressData = new AddressData();

        UserDto userDto = new UserDto();
        userDto.setId(id);
        userDto.setName("USER TEST");
        userDto.setEmail("test@email.com");
        userDto.setBirthDate(LocalDateTime.now());
        userDto.setAddress(addressData.getAddressDto(id));
        return userDto;
    }

    public List<UserDto> getUserDtoList(Integer limit) {
        List<UserDto> userDtoList = new ArrayList<>();
        for (int i = 1; i <= limit; i++) {
            userDtoList.add(getUserDto(i));
        }
        return userDtoList;
    }

    public UserJpa getUserJpa(Integer id) {
        AddressData addressData = new AddressData();

        UserJpa userJpa = new UserJpa();
        userJpa.setId(id);
        userJpa.setName("USER TEST");
        userJpa.setEmail("test@email.com");
        userJpa.setBirthDate(LocalDateTime.now());
        userJpa.setAddress(addressData.getAddressJpa(id));
        return userJpa;
    }

    public List<UserJpa> getUserJpaList(Integer limit) {
        List<UserJpa> userJpaList = new ArrayList<>();
        for (int i = 1; i <= limit; i++) {
            userJpaList.add(getUserJpa(i));
        }
        return userJpaList;
    }
}

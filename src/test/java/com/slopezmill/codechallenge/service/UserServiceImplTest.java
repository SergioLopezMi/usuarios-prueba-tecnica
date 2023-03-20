package com.slopezmill.codechallenge.service;

import com.slopezmill.codechallenge.data.AddressData;
import com.slopezmill.codechallenge.data.UserData;
import com.slopezmill.codechallenge.dto.AddressDto;
import com.slopezmill.codechallenge.dto.UserDto;
import com.slopezmill.codechallenge.exception.InvalidDataException;
import com.slopezmill.codechallenge.exception.InvalidInputException;
import com.slopezmill.codechallenge.exception.UserNotFoundException;
import com.slopezmill.codechallenge.jpa.UserJpa;
import com.slopezmill.codechallenge.mapper.UserMapper;
import com.slopezmill.codechallenge.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    UserRepository userRepository;

    @Mock
    UserMapper userMapper;

    @InjectMocks
    UserServiceImpl userService;

    UserData userData;

    AddressData addressData;

    @BeforeEach
    void setUp() {
        this.userData = new UserData();
        this.addressData = new AddressData();
    }

    @Test
    void getAllUsers() {
        when(this.userRepository.findAll()).thenReturn(this.userData.getUserJpaList(3));
        when(this.userMapper.toUserDto(any())).thenReturn(this.userData.getUserDto(1));

        List<UserDto> result = this.userService.getAllUsers();

        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(3, result.size())
        );
    }

    @Test
    void getUserById_throws_InvalidDataException() {
        assertThrows(InvalidDataException.class, () -> this.userService.getUserById(null));
    }

    @Test
    void getUserById_throws_UserNotFoundException() {
        when(this.userRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> this.userService.getUserById(1));
    }

    @Test
    void getUserById() throws InvalidDataException, UserNotFoundException {
        when(this.userRepository.findById(any())).thenReturn(Optional.of(this.userData.getUserJpa(1)));
        when(this.userMapper.toUserDto(any())).thenReturn(this.userData.getUserDto(1));

        UserDto result = this.userService.getUserById(1);

        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(1, result.getId())
        );
    }

    @Test
    void createUser_invalid_user_throws_InvalidInputException() {
        UserDto userDto = this.userData.getUserDto(1);
        userDto.setName(null);

        assertThrows(InvalidInputException.class, () -> this.userService.createUser(userDto));
    }

    @Test
    void createUser_invalid_address_throws_InvalidInputException() {
        UserDto userDto = this.userData.getUserDto(1);
        AddressDto addressDto = this.addressData.getAddressDto(1);
        addressDto.setStreet(null);
        userDto.setAddress(addressDto);

        assertThrows(InvalidInputException.class, () -> this.userService.createUser(userDto));
    }

    @Test
    void createUser() throws InvalidInputException {
        UserJpa userJpa = this.userData.getUserJpa(1);

        UserDto userDto = this.userData.getUserDto(1);
        userDto.setAddress(this.addressData.getAddressDto(1));

        when(this.userMapper.toUserJpa(any())).thenReturn(userJpa);
        when(this.userRepository.save(any())).thenReturn(userJpa);
        when(this.userMapper.toUserDto(any())).thenReturn(userDto);

        UserDto result = this.userService.createUser(userDto);

        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(1, result.getId())
        );
    }

    @Test
    void updateUserById_id_null_throws_InvalidDataException() {
        UserDto userDto = this.userData.getUserDto(1);

        assertThrows(InvalidDataException.class, () -> this.userService.updateUserById(null, userDto));
    }

    @Test
    void updateUserById_user_not_valid_throws_InvalidInputException() {
        UserDto userDto = this.userData.getUserDto(1);
        userDto.setName(null);

        assertThrows(InvalidInputException.class, () -> this.userService.updateUserById(1, userDto));
    }

    @Test
    void updateUserById_address_not_valid_throws_InvalidInputException() {
        UserDto userDto = this.userData.getUserDto(1);
        AddressDto addressDto = this.addressData.getAddressDto(1);
        addressDto.setStreet(null);
        userDto.setAddress(addressDto);

        assertThrows(InvalidInputException.class, () -> this.userService.updateUserById(1, userDto));
    }

    @Test
    void updateUserById_throws_UserNotFoundException() {
        UserDto userDto = this.userData.getUserDto(1);
        userDto.setAddress(this.addressData.getAddressDto(1));

        when(this.userRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> this.userService.updateUserById(1, userDto));
    }

    @Test
    void updateUserById() throws InvalidDataException, InvalidInputException, UserNotFoundException {
        UserJpa userJpa = this.userData.getUserJpa(1);
        userJpa.setAddress(this.addressData.getAddressJpa(1));

        UserDto userDto = this.userData.getUserDto(1);
        userDto.setAddress(this.addressData.getAddressDto(1));

        when(this.userRepository.findById(any())).thenReturn(Optional.of(userJpa));
        when(this.userMapper.toUserJpa(any())).thenReturn(userJpa);
        when(this.userRepository.save(any())).thenReturn(userJpa);
        when(this.userMapper.toUserDto(any())).thenReturn(userDto);

        UserDto result = this.userService.updateUserById(1, userDto);

        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(1, result.getId())
        );
    }

    @Test
    void deleteUserById_throws_InvalidDataException() {
        assertThrows(InvalidDataException.class, () -> this.userService.deleteUserById(null));
    }

    @Test
    void deleteUserById_throws_UserNotFoundException() {
        when(this.userRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> this.userService.deleteUserById(1));
    }

    @Test
    void deleteUserById() throws InvalidDataException, UserNotFoundException {

        when(this.userRepository.findById(any())).thenReturn(Optional.of(this.userData.getUserJpa(1)));
        doNothing().when(this.userRepository).delete(any());

        this.userService.deleteUserById(1);

        verify(this.userRepository, times(1)).delete(any());
    }

}
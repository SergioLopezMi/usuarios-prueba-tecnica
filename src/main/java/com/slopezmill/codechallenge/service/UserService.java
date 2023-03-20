package com.slopezmill.codechallenge.service;

import com.slopezmill.codechallenge.dto.UserDto;
import com.slopezmill.codechallenge.exception.InvalidDataException;
import com.slopezmill.codechallenge.exception.InvalidInputException;
import com.slopezmill.codechallenge.exception.UserNotFoundException;

import java.util.List;

public interface UserService {

    List<UserDto> getAllUsers();

    UserDto getUserById(Integer id) throws InvalidDataException, UserNotFoundException;

    UserDto createUser(UserDto userDto) throws InvalidInputException;

    UserDto updateUserById(Integer id, UserDto userDto) throws InvalidDataException, InvalidInputException, UserNotFoundException;

    void deleteUserById(Integer id) throws InvalidDataException, UserNotFoundException;
}

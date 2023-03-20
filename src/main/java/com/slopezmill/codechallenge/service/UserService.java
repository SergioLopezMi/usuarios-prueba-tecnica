package com.slopezmill.codechallenge.service;

import com.slopezmill.codechallenge.dto.UserDto;
import com.slopezmill.codechallenge.exception.InvalidDataException;
import com.slopezmill.codechallenge.exception.InvalidInputException;
import com.slopezmill.codechallenge.exception.UserException;

import java.util.List;

public interface UserService {

    List<UserDto> getAllUsers();

    UserDto getUserById(Integer id) throws InvalidDataException, UserException;

    UserDto createUser(UserDto userDto) throws InvalidInputException;

    UserDto updateUserById(Integer id, UserDto userDto) throws InvalidDataException, InvalidInputException, UserException;

    void deleteUserById(Integer id) throws InvalidDataException, UserException;
}

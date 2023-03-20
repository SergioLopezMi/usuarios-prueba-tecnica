package com.slopezmill.codechallenge.controller;

import com.slopezmill.codechallenge.data.UserData;
import com.slopezmill.codechallenge.dto.UserDto;
import com.slopezmill.codechallenge.exception.InvalidDataException;
import com.slopezmill.codechallenge.exception.InvalidInputException;
import com.slopezmill.codechallenge.exception.UserNotFoundException;
import com.slopezmill.codechallenge.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    UserService userService;

    @InjectMocks
    UserController controller;

    UserData userData;

    @BeforeEach
    void setUp() {
        this.userData = new UserData();
    }

    @Test
    void getAllUsers() {
        when(this.userService.getAllUsers()).thenReturn(this.userData.getUserDtoList(3));

        ResponseEntity<List<UserDto>> result = this.controller.getAllUsers();

        assertEquals(3, result.getBody().size());
        assertEquals(200, result.getStatusCodeValue());
    }

    @Test
    void createUser_trhows_InvalidInputException() throws InvalidInputException {
        UserDto userDto = this.userData.getUserDto(1);
        userDto.setName(null);

        when(this.userService.createUser(any())).thenThrow(InvalidInputException.class);

        assertThrows(InvalidInputException.class, () -> this.controller.createUser(userDto));
    }

    @Test
    void createUser() throws InvalidInputException {
        UserDto userDto = this.userData.getUserDto(1);

        when(this.userService.createUser(any())).thenReturn(userDto);

        ResponseEntity<UserDto> result = this.controller.createUser(userDto);

        assertEquals(201, result.getStatusCodeValue());
        assertEquals(userDto, result.getBody());
    }

    @Test
    void getUserById_throws_InvalidDataException() throws InvalidDataException, UserNotFoundException {
        when(this.userService.getUserById(any())).thenThrow(InvalidDataException.class);

        assertThrows(InvalidDataException.class, () -> this.controller.getUserById(null));
    }

    @Test
    void getUserById_throws_UserException() throws InvalidDataException, UserNotFoundException {
        when(this.userService.getUserById(any())).thenThrow(UserNotFoundException.class);

        assertThrows(UserNotFoundException.class, () -> this.controller.getUserById(1));
    }

    @Test
    void getUserById() throws InvalidDataException, UserNotFoundException {
        UserDto userDto = this.userData.getUserDto(1);

        when(this.userService.getUserById(any())).thenReturn(userDto);

        ResponseEntity<UserDto> result = this.controller.getUserById(1);

        assertEquals(200, result.getStatusCodeValue());
        assertEquals(userDto, result.getBody());
    }

    @Test
    void updateUserById_throws_InvalidDataException() throws InvalidDataException, InvalidInputException, UserNotFoundException {
        UserDto userDto = this.userData.getUserDto(1);

        when(this.userService.updateUserById(any(), any())).thenThrow(InvalidDataException.class);

        assertThrows(InvalidDataException.class, () -> this.controller.updateUserById(null, userDto));
    }

    @Test
    void updateUserById_throws_InvalidInputException() throws InvalidDataException, InvalidInputException, UserNotFoundException {
        UserDto userDto = this.userData.getUserDto(1);
        userDto.setName(null);

        when(this.userService.updateUserById(any(), any())).thenThrow(InvalidInputException.class);

        assertThrows(InvalidInputException.class, () -> this.controller.updateUserById(1, userDto));
    }

    @Test
    void updateUserById_throws_UserException() throws InvalidDataException, InvalidInputException, UserNotFoundException {
        UserDto userDto = this.userData.getUserDto(1);

        when(this.userService.updateUserById(any(), any())).thenThrow(UserNotFoundException.class);

        assertThrows(UserNotFoundException.class, () -> this.controller.updateUserById(1, userDto));
    }

    @Test
    void updateUserById() throws InvalidDataException, InvalidInputException, UserNotFoundException {
        UserDto userDto = this.userData.getUserDto(1);
        UserDto userDtoUpdated = this.userData.getUserDto(2);

        when(this.userService.updateUserById(any(), any())).thenReturn(userDtoUpdated);

        ResponseEntity<UserDto> result = this.controller.updateUserById(1, userDto);

        assertEquals(200, result.getStatusCodeValue());
        assertEquals(userDtoUpdated, result.getBody());
    }

    @Test
    void deleteUserById_throws_InvalidDataException() throws InvalidDataException, UserNotFoundException {
        doThrow(InvalidDataException.class).when(this.userService).deleteUserById(any());

        assertThrows(InvalidDataException.class, () -> this.controller.deleteUserById(null));
    }

    @Test
    void deleteUserById_throws_UserException() throws InvalidDataException, UserNotFoundException {
        doThrow(UserNotFoundException.class).when(this.userService).deleteUserById(any());

        assertThrows(UserNotFoundException.class, () -> this.controller.deleteUserById(1));
    }

    @Test
    void deleteUserById() throws InvalidDataException, UserNotFoundException {
        doNothing().when(this.userService).deleteUserById(any());

        ResponseEntity<Void> result = this.controller.deleteUserById(1);

        assertEquals(200, result.getStatusCodeValue());
    }

}
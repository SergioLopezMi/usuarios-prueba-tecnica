package com.slopezmill.codechallenge.controller;

import com.slopezmill.codechallenge.dto.UserDto;
import com.slopezmill.codechallenge.exception.InvalidDataException;
import com.slopezmill.codechallenge.exception.InvalidInputException;
import com.slopezmill.codechallenge.exception.UserException;
import com.slopezmill.codechallenge.service.UserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    private final @NonNull UserService userService;

    @GetMapping("/getusers")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        LOGGER.info("[controller - getAllUsers] - Start get all users");
        return new ResponseEntity<>(this.userService.getAllUsers(), HttpStatus.OK);
    }

    @PostMapping("/createUsers")
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) throws InvalidInputException {
        LOGGER.info("[controller - createUser] - Start create user {}", userDto);
        return new ResponseEntity<>(this.userService.createUser(userDto), HttpStatus.CREATED);
    }

    @GetMapping("/getusersById/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<UserDto> getUserById(@PathVariable Integer id)
            throws InvalidDataException, UserException {
        LOGGER.info("[controller - getUserById] - Start get user by id{}", id);
        return new ResponseEntity<>(this.userService.getUserById(id), HttpStatus.OK);
    }

    @PutMapping("/updateUsersById/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<UserDto> updateUserById(@PathVariable Integer id, @RequestBody UserDto userDto)
            throws InvalidInputException, InvalidDataException, UserException {
        LOGGER.info("[controller - updateUserById] - Start update user by id {}", id);
        return new ResponseEntity<>(this.userService.updateUserById(id, userDto), HttpStatus.OK);
    }

    @DeleteMapping("/deleteUsersById/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<Void> deleteUserById(@PathVariable Integer id)
            throws InvalidDataException, UserException {
        LOGGER.info("[controller - deleteUserById] - Start delete user by id {}", id);
        this.userService.deleteUserById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

package com.slopezmill.codechallenge.service;

import com.slopezmill.codechallenge.constant.Errors;
import com.slopezmill.codechallenge.dto.AddressDto;
import com.slopezmill.codechallenge.dto.UserDto;
import com.slopezmill.codechallenge.exception.InvalidDataException;
import com.slopezmill.codechallenge.exception.InvalidInputException;
import com.slopezmill.codechallenge.exception.UserNotFoundException;
import com.slopezmill.codechallenge.jpa.UserJpa;
import com.slopezmill.codechallenge.mapper.UserMapper;
import com.slopezmill.codechallenge.repository.UserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    private final @NonNull UserRepository userRepository;

    private final @NonNull UserMapper userMapper;

    @Override
    public List<UserDto> getAllUsers() {
        LOGGER.info("[getAllUsers] - Start get all users");
        return this.userRepository.findAll().stream()
                .map(this.userMapper::toUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto getUserById(Integer id) throws InvalidDataException, UserNotFoundException {
        LOGGER.info("[getUserById] - Start get user by id : {}", id);

        if (id == null) {
            throw new InvalidDataException(Errors.INVALID_USER_ID);
        }

        final UserJpa userJpa = this.userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(Errors.USER_NOT_FOUND));

        LOGGER.info("[getUserById] - End get user by id");
        return this.userMapper.toUserDto(userJpa);
    }

    @Override
    public UserDto createUser(UserDto userDto) throws InvalidInputException {
        LOGGER.info("[createUser] - Start create user :{}", userDto);
        if (Boolean.FALSE.equals(this.validateUser(userDto)) ||
                Boolean.FALSE.equals(this.validateAddress(userDto.getAddress()))) {
            throw new InvalidInputException(Errors.INVALID_INPUT);
        }
        UserJpa addedUser = this.userRepository.save(userMapper.toUserJpa(userDto));

        LOGGER.info("[createUser] - End create user");
        return this.userMapper.toUserDto(addedUser);
    }

    @Override
    public UserDto updateUserById(Integer id, UserDto userDto)
            throws InvalidDataException, InvalidInputException, UserNotFoundException {
        LOGGER.info("[updateUserById] - Start update user by id : {}", id);
        if (id == null) {
            throw new InvalidDataException(Errors.INVALID_USER_ID);
        }

        if (Boolean.FALSE.equals(this.validateUser(userDto)) ||
                Boolean.FALSE.equals(this.validateAddress(userDto.getAddress()))) {
            throw new InvalidInputException(Errors.INVALID_INPUT);
        }

        this.userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(Errors.USER_NOT_FOUND));

        LOGGER.info("[updateUserById] - End update user by id");
        return this.userMapper.toUserDto(this.userRepository.save(this.userMapper.toUserJpa(userDto)));
    }

    @Override
    public void deleteUserById(Integer id) throws InvalidDataException, UserNotFoundException {
        LOGGER.info("[deleteUserById] - Start delete user by id : {}", id);
        if (id == null) {
            throw new InvalidDataException(Errors.INVALID_USER_ID);
        }
        final UserJpa userJpa = this.userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(Errors.USER_NOT_FOUND));

        LOGGER.info("[deleteUserById] - End delete user by id");
        this.userRepository.delete(userJpa);
    }


    private Boolean validateUser(UserDto userDto) {
        return userDto.getName() != null && userDto.getEmail() != null && userDto.getBirthDate() != null &&
                userDto.getAddress() != null;
    }

    private Boolean validateAddress(AddressDto addressDto) {
        return addressDto.getStreet() != null && addressDto.getCity() != null && addressDto.getCountry() != null;
    }
}

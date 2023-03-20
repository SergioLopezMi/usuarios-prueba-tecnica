package com.slopezmill.codechallenge.controller.handler;

import com.slopezmill.codechallenge.exception.InvalidDataException;
import com.slopezmill.codechallenge.exception.InvalidInputException;
import com.slopezmill.codechallenge.exception.UserNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class RestApiExceptionHandlerTest {

    @InjectMocks
    private RestApiExceptionHandler restApiExceptionHandler;

    @Test
    void handleInvalidInputException() {
        ResponseEntity<Object> result = this.restApiExceptionHandler
                .handleInvalidInputException(new InvalidInputException("test"));

        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(405, result.getStatusCodeValue()));
    }

    @Test
    void handleInvalidDataException() {
        ResponseEntity<Object> result = this.restApiExceptionHandler
                .handleInvalidDataException(new InvalidDataException("test"));

        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(400, result.getStatusCodeValue()));
    }

    @Test
    void handleUserNotFoundException() {
        ResponseEntity<Object> result = this.restApiExceptionHandler
                .handleUserNotFoundException(new UserNotFoundException("test"));

        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(404, result.getStatusCodeValue()));
    }
}
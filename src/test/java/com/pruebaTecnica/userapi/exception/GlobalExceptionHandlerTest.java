package com.pruebaTecnica.userapi.exception;

import org.junit.jupiter.api.Test;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.*;

class GlobalExceptionHandlerTest {

    @Test
    void shouldThrowNotFound() {

        ResponseStatusException ex = new ResponseStatusException(org.springframework.http.HttpStatus.NOT_FOUND,
                "error");

        assertEquals(404, ex.getStatusCode().value());
    }

    @Test
    void shouldThrowBadRequest() {

        ResponseStatusException ex = new ResponseStatusException(org.springframework.http.HttpStatus.BAD_REQUEST,
                "error");

        assertEquals(400, ex.getStatusCode().value());
    }
}
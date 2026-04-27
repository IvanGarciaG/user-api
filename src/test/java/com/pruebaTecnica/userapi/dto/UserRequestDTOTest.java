package com.pruebaTecnica.userapi.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserRequestDTOTest {

    @Test
    void shouldSetAndGetValues() {

        UserRequestDTO dto = new UserRequestDTO();

        dto.setEmail("test@mail.com");
        dto.setName("test");
        dto.setPassword("1234");

        assertEquals("test@mail.com", dto.getEmail());
        assertEquals("test", dto.getName());
        assertEquals("1234", dto.getPassword());
    }
}
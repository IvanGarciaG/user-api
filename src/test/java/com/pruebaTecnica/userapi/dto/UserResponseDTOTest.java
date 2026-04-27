package com.pruebaTecnica.userapi.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserResponseDTOTest {

    @Test
    void shouldCreateDtoWithBuilder() {

        UserResponseDTO dto = UserResponseDTO.builder()
                .email("test@mail.com")
                .name("test")
                .phone("+521234567890")
                .taxId("RFC123")
                .build();

        assertEquals("test@mail.com", dto.getEmail());
        assertEquals("test", dto.getName());
        assertEquals("+521234567890", dto.getPhone());
        assertEquals("RFC123", dto.getTaxId());
    }

    @Test
    void shouldUseSettersAndGetters() {

        UserResponseDTO dto = new UserResponseDTO();

        dto.setEmail("setter@mail.com");
        dto.setName("setter");

        assertEquals("setter@mail.com", dto.getEmail());
        assertEquals("setter", dto.getName());
    }
}
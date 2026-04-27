package com.pruebaTecnica.userapi.service;

import com.pruebaTecnica.userapi.dto.UserRequestDTO;
import com.pruebaTecnica.userapi.dto.UserResponseDTO;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.UUID;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    private UserService userService;

    @BeforeEach
    void setUp() {
        userService = new UserService();
        userService.init(); // 👈 importante para datos mock
    }

    @Test
    void shouldReturnUsers() {
        List<UserResponseDTO> users = userService.getAllUsers(null, null);

        assertNotNull(users);
        assertFalse(users.isEmpty());
    }

    @Test
    void shouldFilterByName() {
        List<UserResponseDTO> users = userService.getAllUsers(null, "name+eq+user1");

        assertEquals(1, users.size());
        assertEquals("user1", users.get(0).getName());
    }

    @Test
    void shouldSortByEmail() {
        List<UserResponseDTO> users = userService.getAllUsers("email", null);

        assertEquals("user1@mail.com", users.get(0).getEmail());
    }

    // 🔥 NUEVO: CREATE
    @Test
    void shouldCreateUser() {
        UserRequestDTO request = new UserRequestDTO();
        request.setEmail("new@mail.com");
        request.setName("newUser");
        request.setPhone("+1234567890");
        request.setPassword("1234");
        request.setTaxId("ABCD123456XYZ");

        UserResponseDTO created = userService.createUser(request);

        assertNotNull(created);
        assertEquals("new@mail.com", created.getEmail());
    }

    // 🔥 NUEVO: DELETE
    @Test
    void shouldDeleteUser() {
        List<UserResponseDTO> before = userService.getAllUsers(null, null);

        UUID id = userService.getAllUsers(null, null).get(0).getId();

        userService.deleteUser(id);

        List<UserResponseDTO> after = userService.getAllUsers(null, null);

        assertEquals(before.size() - 1, after.size());
    }

    // 🔥 NUEVO: UPDATE
    @Test
    void shouldUpdateUser() {
        UserRequestDTO request = new UserRequestDTO();
        request.setEmail("updated@mail.com");

        UUID id = userService.getAllUsers(null, null).get(0).getId();

        UserResponseDTO updated = userService.updateUser(id, request);

        assertEquals("updated@mail.com", updated.getEmail());
    }
}
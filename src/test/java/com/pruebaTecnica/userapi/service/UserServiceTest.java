package com.pruebaTecnica.userapi.service;

import com.pruebaTecnica.userapi.dto.LoginRequestDTO;
import com.pruebaTecnica.userapi.dto.UserRequestDTO;
import com.pruebaTecnica.userapi.dto.UserResponseDTO;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    private UserService userService;

    @BeforeEach
    void setUp() {
        userService = new UserService();
        userService.init();
    }
    //  GET USERS

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
    }

    @Test
    void shouldReturnEmptyWhenFilterNoMatch() {
        List<UserResponseDTO> users = userService.getAllUsers(null, "name+eq+NO_EXISTE");

        assertTrue(users.isEmpty());
    }

    @Test
    void shouldHandleInvalidFilter() {
        List<UserResponseDTO> users = userService.getAllUsers(null, "invalid");

        assertNotNull(users);
    }

    @Test
    void shouldSortByEmail() {
        List<UserResponseDTO> users = userService.getAllUsers("email", null);

        assertNotNull(users);
    }

    @Test
    void shouldHandleInvalidSort() {
        List<UserResponseDTO> users = userService.getAllUsers("unknown", null);

        assertNotNull(users);
    }

    //  CREATE


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

    @Test
    void shouldFailCreateWithoutEmail() {
        UserRequestDTO request = new UserRequestDTO();

        assertThrows(ResponseStatusException.class, () -> userService.createUser(request));
    }
    // 🔹 DELETE
    @Test
    void shouldDeleteUser() {
        UUID id = userService.getAllUsers(null, null).get(0).getId();

        userService.deleteUser(id);

        List<UserResponseDTO> after = userService.getAllUsers(null, null);

        assertFalse(after.stream().anyMatch(u -> u.getId().equals(id)));
    }

    @Test
    void shouldFailDeleteWhenUserNotFound() {
        assertThrows(ResponseStatusException.class, () -> userService.deleteUser(UUID.randomUUID()));
    }

    // UPDATE

    @Test
    void shouldUpdateUser() {
        UUID id = userService.getAllUsers(null, null).get(0).getId();

        UserRequestDTO request = new UserRequestDTO();
        request.setEmail("updated@mail.com");

        UserResponseDTO updated = userService.updateUser(id, request);

        assertEquals("updated@mail.com", updated.getEmail());
    }

    @Test
    void shouldFailUpdateWhenUserNotFound() {
        UserRequestDTO request = new UserRequestDTO();

        assertThrows(ResponseStatusException.class, () -> userService.updateUser(UUID.randomUUID(), request));
    }


    // LOGIN

    @Test
    void shouldLoginSuccess() {
        UserResponseDTO user = userService.getAllUsers(null, null).get(0);

        LoginRequestDTO request = new LoginRequestDTO();
        request.setTaxId(user.getTaxId());
        request.setPassword("123456");

        UserResponseDTO result = userService.login(request);

        assertNotNull(result);
    }

    @Test
    void shouldFailLoginWrongPassword() {
        UserResponseDTO user = userService.getAllUsers(null, null).get(0);

        LoginRequestDTO request = new LoginRequestDTO();
        request.setTaxId(user.getTaxId());
        request.setPassword("WRONG");

        assertThrows(ResponseStatusException.class, () -> userService.login(request));
    }

    @Test
    void shouldFailLoginUserNotFound() {
        LoginRequestDTO request = new LoginRequestDTO();
        request.setTaxId("NO_EXISTE");
        request.setPassword("123");

        assertThrows(ResponseStatusException.class, () -> userService.login(request));
    }

    @Test
    void shouldFailLoginNullRequest() {
        assertThrows(ResponseStatusException.class, () -> userService.login(null));
    }

    @Test
    void shouldIgnoreNullFieldsOnUpdate() {

        UUID id = userService.getAllUsers(null, null).get(0).getId();

        UserRequestDTO request = new UserRequestDTO(); 

        UserResponseDTO result = userService.updateUser(id, request);

        assertNotNull(result);
    }
}
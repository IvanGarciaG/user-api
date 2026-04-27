package com.pruebaTecnica.userapi.controller;

import com.pruebaTecnica.userapi.dto.UserResponseDTO;
import com.pruebaTecnica.userapi.service.UserService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.ArgumentMatchers.any;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;
    // GET USERS
    @Test
    void shouldGetUsers() throws Exception {

        List<UserResponseDTO> users = List.of(
                new UserResponseDTO(
                        UUID.randomUUID(),
                        "user1@mail.com",
                        "user1",
                        "+521234567890",
                        "RFC",
                        null,
                        null));
        when(userService.getAllUsers(any(), any()))
                .thenReturn(users);

        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].email").value("user1@mail.com"))
                .andExpect(jsonPath("$[0].name").value("user1"));
    }
    //  CREATE USER
    @Test
    void shouldCreateUser() throws Exception {

        UserResponseDTO user = new UserResponseDTO(
                UUID.randomUUID(),
                "new@mail.com",
                "newUser",
                "+521234567890",
                "ABCD123456XYZ",
                null,
                null);

        when(userService.createUser(any())).thenReturn(user);
        String body = """
                    {
                        "name": "newUser",
                        "email": "new@mail.com",
                        "password": "1234",
                        "phone": "+521234567890",
                        "taxId": "ABCD123456XYZ"
                    }
                """;

        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.email").value("new@mail.com"))
                .andExpect(jsonPath("$.name").value("newUser"));
    }

    //  DELETE USER
    @Test
    void shouldDeleteUser() throws Exception {

        mockMvc.perform(delete("/users/{id}", UUID.randomUUID()))
                .andExpect(status().isNoContent());

        verify(userService, times(1)).deleteUser(any());
    }
    //  UPDATE USER
    @Test
    void shouldUpdateUser() throws Exception {

        UserResponseDTO updated = new UserResponseDTO(
                UUID.randomUUID(),
                "updated@mail.com",
                "updated",
                "+521234567890",
                "ABCD123456XYZ",
                null,
                null);

        when(userService.updateUser(any(), any())).thenReturn(updated);

        String body = """
                    {
                        "email": "updated@mail.com",
                        "name": "updated",
                        "phone": "+521234567890",
                        "taxId": "ABCD123456XYZ",
                        "password": "1234"
                    }
                """;

        mockMvc.perform(patch("/users/{id}", UUID.randomUUID())
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("updated@mail.com"))
                .andExpect(jsonPath("$.name").value("updated"));
    }

    @Test
    void shouldFailValidationMissingFields() throws Exception {

        String body = """
                    {
                        "email": ""
                    }
                """;

        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(status().isBadRequest());
    }

    @Test
void shouldReturn404WhenDeletingNonExistingUser() throws Exception {

    doThrow(new ResponseStatusException(HttpStatus.NOT_FOUND))
            .when(userService).deleteUser(any());

    mockMvc.perform(delete("/users/{id}", UUID.randomUUID()))
            .andExpect(status().isNotFound());
}
}
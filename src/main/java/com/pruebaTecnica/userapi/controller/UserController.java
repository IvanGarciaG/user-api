package com.pruebaTecnica.userapi.controller;

import com.pruebaTecnica.userapi.dto.UserRequestDTO;
import com.pruebaTecnica.userapi.dto.UserResponseDTO;
import com.pruebaTecnica.userapi.model.User;
import com.pruebaTecnica.userapi.service.UserService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserResponseDTO> getUsers(
            @RequestParam(required = false) String sortedBy,
            @RequestParam(required = false) String filter) {
        return userService.getAllUsers(sortedBy, filter);
    }

    @PostMapping
public UserResponseDTO createUser(@Valid @RequestBody UserRequestDTO request) {
    return userService.createUser(request);
}

@DeleteMapping("/{id}")
public void deleteUser(@PathVariable UUID id) {
    userService.deleteUser(id);
}

@PatchMapping("/{id}")
public UserResponseDTO updateUser(
        @PathVariable UUID id,
        @RequestBody UserRequestDTO request) {
    return userService.updateUser(id, request);
}


}
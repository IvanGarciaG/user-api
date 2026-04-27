package com.pruebaTecnica.userapi.controller;

import com.pruebaTecnica.userapi.dto.LoginRequestDTO;
import com.pruebaTecnica.userapi.dto.UserResponseDTO;
import com.pruebaTecnica.userapi.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public UserResponseDTO login(@RequestBody LoginRequestDTO request) {
        return userService.login(request);
    }
}
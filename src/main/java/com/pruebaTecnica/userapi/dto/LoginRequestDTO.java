package com.pruebaTecnica.userapi.dto;

import lombok.Data;

@Data
public class LoginRequestDTO {
    private String taxId;
    private String password;
}
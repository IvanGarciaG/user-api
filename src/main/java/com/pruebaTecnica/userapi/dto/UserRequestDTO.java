package com.pruebaTecnica.userapi.dto;

import lombok.Data;
import java.util.List;
import jakarta.validation.constraints.*;
import com.pruebaTecnica.userapi.model.Address;


@Data
public class UserRequestDTO {

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String name;

    @NotBlank
    @Pattern(regexp = "^(\\+\\d{1,3})?\\s?\\d{10}$", message = "Invalid phone format")
    private String phone;

    @NotBlank
    private String password;

    @NotBlank
    @Pattern(regexp = "^[A-Z]{4}\\d{6}[A-Z0-9]{3}$", message = "Invalid RFC format")
    private String taxId;

    private List<Address> addresses;
}
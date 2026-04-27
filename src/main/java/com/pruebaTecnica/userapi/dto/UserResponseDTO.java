package com.pruebaTecnica.userapi.dto;

import com.pruebaTecnica.userapi.model.Address;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL) 
public class UserResponseDTO {

    private UUID id;

    private String email;

    private String name;

    private String phone;

    private String taxId;

    private LocalDateTime createdAt;

    private List<Address> addresses;
}
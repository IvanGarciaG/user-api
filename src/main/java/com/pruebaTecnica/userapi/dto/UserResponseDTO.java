package com.pruebaTecnica.userapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import com.pruebaTecnica.userapi.model.Address;

@Data
@NoArgsConstructor 
@AllArgsConstructor 
@Builder 
public class UserResponseDTO {

    private UUID id;
    private String email;
    private String name;
    private String phone;
    private String taxId;
    private LocalDateTime createdAt;
    private List<Address> addresses;
}
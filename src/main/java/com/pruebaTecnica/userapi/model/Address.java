package com.pruebaTecnica.userapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@AllArgsConstructor 
@NoArgsConstructor
public class Address {
    private Long id;
    private String name;
    private String street;
    private String countryCode;
}
package com.pruebaTecnica.userapi.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddressTest {

    @Test
    void shouldSetAndGetValues() {

        Address address = new Address();

        address.setId(1L);
        address.setName("Casa");
        address.setStreet("Main Street");
        address.setCountryCode("MX");

        assertEquals(1L, address.getId());
        assertEquals("Casa", address.getName());
        assertEquals("Main Street", address.getStreet());
        assertEquals("MX", address.getCountryCode());
    }
}
package com.pruebaTecnica.userapi.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EncryptionUtilTest {

    @Test
    void shouldEncryptAndDecrypt() {

        String original = "123456";

        String encrypted = EncryptionUtil.encrypt(original);
        String decrypted = EncryptionUtil.decrypt(encrypted);

        assertEquals(original, decrypted);
    }

    @Test
    void shouldNotMatchWrongValue() {

        String encrypted = EncryptionUtil.encrypt("123");

        String decrypted = EncryptionUtil.decrypt(encrypted);

        assertNotEquals("wrong", decrypted);
    }
}
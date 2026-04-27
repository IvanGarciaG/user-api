package com.pruebaTecnica.userapi.util;

import com.pruebaTecnica.userapi.model.User;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class FilterUtilTest {

    @Test
    void shouldFilterByName() {

        User user = new User();
        user.setId(UUID.randomUUID());
        user.setName("ivan");

        Stream<User> stream = Stream.of(user);

        Stream<User> result = FilterUtil.applyFilter(stream, "name+eq+ivan");

        List<User> list = result.toList();

        assertEquals(1, list.size());
    }

    @Test
    void shouldReturnAllWhenFilterNull() {

        User user = new User();

        Stream<User> stream = Stream.of(user);

        Stream<User> result = FilterUtil.applyFilter(stream, null);

        assertEquals(1, result.toList().size());
    }

    @Test
    void shouldReturnAllWhenFilterInvalid() {

        User user = new User();

        Stream<User> stream = Stream.of(user);

        Stream<User> result = FilterUtil.applyFilter(stream, "invalid");

        assertEquals(1, result.toList().size());
    }

    @Test
    void shouldReturnEmptyWhenNoMatch() {

        User user = new User();
        user.setName("ivan");

        Stream<User> stream = Stream.of(user);

        Stream<User> result = FilterUtil.applyFilter(stream, "name+eq+otro");

        assertTrue(result.toList().isEmpty());
    }

    @Test
    void shouldHandleNullStream() {
        assertThrows(Exception.class, () -> FilterUtil.applyFilter(null, "name+eq+ivan"));
    }

    @Test
    void shouldHandleMalformedFilter() {
        var result = FilterUtil.applyFilter(Stream.empty(), "name+++");

        assertNotNull(result);
    }
}
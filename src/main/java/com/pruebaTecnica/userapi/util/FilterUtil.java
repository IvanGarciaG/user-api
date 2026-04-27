package com.pruebaTecnica.userapi.util;

import com.pruebaTecnica.userapi.model.User;

import java.util.stream.Stream;

public class FilterUtil {

    public static Stream<User> applyFilter(Stream<User> stream, String filter) {

        if (filter == null || filter.isBlank())
            return stream;

        String[] parts = filter.split("[+ ]");

        if (parts.length != 3)
            return stream;

        String field = parts[0];
        String operator = parts[1];
        String value = parts[2];

        return stream.filter(user -> {

            String fieldValue = getFieldValue(user, field);

            if (fieldValue == null)
                return false;

            return switch (operator) {
                case "co" -> fieldValue.contains(value);
                case "eq" -> fieldValue.equals(value);
                case "sw" -> fieldValue.startsWith(value);
                case "ew" -> fieldValue.endsWith(value);
                default -> false;
            };
        });
    }

    private static String getFieldValue(User user, String field) {
        return switch (field) {
            case "email" -> user.getEmail();
            case "name" -> user.getName();
            case "phone" -> user.getPhone();
            case "tax_id" -> user.getTaxId();
            default -> null;
        };
    }
}
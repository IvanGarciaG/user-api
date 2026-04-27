package com.pruebaTecnica.userapi.service;

import com.pruebaTecnica.userapi.dto.LoginRequestDTO;
import com.pruebaTecnica.userapi.dto.UserRequestDTO;
import com.pruebaTecnica.userapi.dto.UserResponseDTO;
import com.pruebaTecnica.userapi.model.Address;
import com.pruebaTecnica.userapi.model.User;
import com.pruebaTecnica.userapi.util.EncryptionUtil;
import com.pruebaTecnica.userapi.util.FilterUtil;

import jakarta.annotation.PostConstruct;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Stream;

@Service
public class UserService {

    private List<User> users = new ArrayList<>();
    private ZoneId zone = ZoneId.of("Indian/Antananarivo");

    @PostConstruct
    public void init() {

        users.add(new User(
                UUID.randomUUID(),
                "user3@mail.com",
                "user3",
                "+1 55 555 555 55",
                EncryptionUtil.encrypt("123456"),
                "AARR990101XXX",
                LocalDateTime.now(zone),
                List.of(
                        new Address(1L, "workaddress", "street No. 1", "UK"),
                        new Address(2L, "homeaddress", "street No. 2", "AU"))));

        users.add(new User(
                UUID.randomUUID(),
                "user2@mail.com",
                "user2",
                "+1 55 555 555 56",
                EncryptionUtil.encrypt("123456"),
                "BBBB990101XXX",
                LocalDateTime.now(zone),
                Collections.emptyList()));

        users.add(new User(
                UUID.randomUUID(),
                "user1@mail.com",
                "user1",
                "+2 55 555 555 57",
                EncryptionUtil.encrypt("123456"),
                "CCCC990101XXX",
                LocalDateTime.now(zone),
                Collections.emptyList()));
    }

    public List<UserResponseDTO> getAllUsers(String sortedBy, String filter) {

        Stream<User> stream = users.stream();

        //  FILTER
        stream = FilterUtil.applyFilter(stream, filter);

        //  SORT
        stream = applySorting(stream, sortedBy);

        //  MAP
        return stream
                .map(this::mapToDTO)
                .toList();
    }

    //  SORT METHOD
    private Stream<User> applySorting(Stream<User> stream, String sortedBy) {

        if (sortedBy == null || sortedBy.isBlank())
            return stream;

        return switch (sortedBy) {
            case "email" -> stream.sorted(Comparator.comparing(User::getEmail));
            case "name" -> stream.sorted(Comparator.comparing(User::getName));
            case "id" -> stream.sorted(Comparator.comparing(User::getId));
            case "phone" -> stream.sorted(Comparator.comparing(User::getPhone));
            case "tax_id" -> stream.sorted(Comparator.comparing(User::getTaxId));
            case "created_at" -> stream.sorted(Comparator.comparing(User::getCreatedAt));
            default -> stream;
        };
    }

    //  DTO MAPPER
    private UserResponseDTO mapToDTO(User user) {
        return new UserResponseDTO(
                user.getId(),
                user.getEmail(),
                user.getName(),
                user.getPhone(),
                user.getTaxId(),
                user.getCreatedAt(),
                user.getAddresses());
    }

    public UserResponseDTO createUser(UserRequestDTO request) {

    User user = new User(
            UUID.randomUUID(),
            request.getEmail(),
            request.getName(),
            request.getPhone(),
            EncryptionUtil.encrypt(request.getPassword()),
            request.getTaxId(),
            LocalDateTime.now(zone),
            request.getAddresses() != null ? request.getAddresses() : Collections.emptyList()
    );

    users.add(user);

    return mapToDTO(user);
}

public void deleteUser(UUID id) {
    users.removeIf(user -> user.getId().equals(id));
}


public UserResponseDTO updateUser(UUID id, UserRequestDTO request) {

    User user = users.stream()
            .filter(u -> u.getId().equals(id))
            .findFirst()
            .orElseThrow();

    if (request.getEmail() != null)
        user.setEmail(request.getEmail());

    if (request.getName() != null)
        user.setName(request.getName());

    if (request.getPhone() != null)
        user.setPhone(request.getPhone());

    if (request.getPassword() != null)
        user.setPassword(EncryptionUtil.encrypt(request.getPassword()));

    if (request.getTaxId() != null)
        user.setTaxId(request.getTaxId());

    if (request.getAddresses() != null)
        user.setAddresses(request.getAddresses());

    return mapToDTO(user);
}


public UserResponseDTO login(LoginRequestDTO request) {

    User user = users.stream()
            .filter(u -> u.getTaxId().equals(request.getTaxId()))
            .findFirst()
            .orElseThrow(() -> new RuntimeException("User not found"));

    String decryptedPassword = EncryptionUtil.decrypt(user.getPassword());

    if (!decryptedPassword.equals(request.getPassword())) {
        throw new RuntimeException("Invalid credentials");
    }

    return mapToDTO(user);
}

}
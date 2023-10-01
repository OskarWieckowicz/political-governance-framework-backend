package com.pgf.politicalgovernanceframeworkbackend.controller;

import com.pgf.politicalgovernanceframeworkbackend.dto.AddressDto;
import com.pgf.politicalgovernanceframeworkbackend.dto.ProfileDto;
import com.pgf.politicalgovernanceframeworkbackend.entity.keycloak.User;
import com.pgf.politicalgovernanceframeworkbackend.repository.keycloak.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
class UserController {
    private final UserRepository repository;

    @GetMapping
    ProfileDto getUserProfile() {
        List<User> all = repository.findAll();
        return ProfileDto.builder()
            .firstName("Oskar")
            .lastName("Więckowicz")
            .taxId("1982371")
            .address(AddressDto.builder()
                .country("Poland")
                .city("Kielce")
                .postalCode("50-123")
                .street("Sliczna 44/12")
                .build())
            .phone("123123123")
            .mail("oskar123@gmail.com").build();
    }
}


package com.pgf.politicalgovernanceframeworkbackend.controller;

import com.pgf.politicalgovernanceframeworkbackend.dto.AddressDto;
import com.pgf.politicalgovernanceframeworkbackend.dto.ProfileDto;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/users")
class UserController {

    @GetMapping
    ProfileDto getUserProfile() {
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

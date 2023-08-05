package com.pgf.politicalgovernanceframeworkbackend.controller;

import com.pgf.politicalgovernanceframeworkbackend.dto.ProfileDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/users")
class UserController {

    @GetMapping
    ProfileDto getUserProfile() {
        return ProfileDto.builder()
                .name("Oskar")
                .lastName("Więckowicz")
                .taxId("1982371")
                .country("Poland")
                .city("Kielce")
                .street("Sliczna 44/12")
                .postal("50-123")
                .phone("123123123")
                .mail("oskar123@gmail.com").build();
    }
}

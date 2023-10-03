package com.pgf.politicalgovernanceframeworkbackend.controller;

import com.pgf.politicalgovernanceframeworkbackend.converter.UserToProfileConverter;
import com.pgf.politicalgovernanceframeworkbackend.dto.AddressDto;
import com.pgf.politicalgovernanceframeworkbackend.fto.ProfileFto;
import com.pgf.politicalgovernanceframeworkbackend.dto.UserDto;
import com.pgf.politicalgovernanceframeworkbackend.service.UserService;
import java.security.Principal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
class UserController {

    private final UserService userService;
    private final UserToProfileConverter converter;

    @GetMapping
    ProfileFto getUserProfile(Principal principal) {
        return converter.userDtoToProfileFto(userService.findById(principal.getName()));
        // TODO: zrobic findById(principal.name)
        // TODO: od nowa eksportowac ustawienia keycloaka zeby uzywalo custom formularza itp. Poprawic tax id generator, oczyscic projekt pod wzgledem spi
//        return ProfileFto.builder()
//            .firstName("Oskar")
//            .lastName("Więckowicz")
//            .taxId("1982371")
//            .address(AddressDto.builder()
//                .country("Poland")
//                .city("Kielce")
//                .postalCode("50-123")
//                .street("Sliczna 44/12")
//                .build())
//            .phone("123123123")
//            .mail("oskar123@gmail.com").build();
    }
}


package com.pgf.politicalgovernanceframeworkbackend.controller;

import com.pgf.politicalgovernanceframeworkbackend.converter.UserToProfileConverter;
import com.pgf.politicalgovernanceframeworkbackend.fto.ProfileFto;
import com.pgf.politicalgovernanceframeworkbackend.service.impl.UserServiceImpl;
import java.security.Principal;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
class UserController {

    private final UserServiceImpl userService;
    private final UserToProfileConverter converter;

    @GetMapping
    ProfileFto getUserProfile(Principal principal) {
        return converter.userDtoToProfileFto(userService.findById(principal.getName()));
    }
}


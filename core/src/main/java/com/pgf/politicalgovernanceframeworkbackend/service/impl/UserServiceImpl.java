package com.pgf.politicalgovernanceframeworkbackend.service.impl;

import com.pgf.politicalgovernanceframeworkbackend.converter.UserConverter;
import com.pgf.politicalgovernanceframeworkbackend.dto.UserDto;
import com.pgf.politicalgovernanceframeworkbackend.exception.NotFoundException;
import com.pgf.politicalgovernanceframeworkbackend.repository.keycloak.UserRepository;
import com.pgf.politicalgovernanceframeworkbackend.service.UserService;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final UserConverter converter;

    public UserDto findById(String id) {
        return repository.findById(id).stream()
            .map(converter::userEntityToUserDto)
            .findFirst()
            .orElseThrow(() -> new NotFoundException("User not found"));
    }
}

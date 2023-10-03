package com.pgf.politicalgovernanceframeworkbackend.service;

import com.pgf.politicalgovernanceframeworkbackend.converter.UserConverter;
import com.pgf.politicalgovernanceframeworkbackend.dto.UserDto;
import com.pgf.politicalgovernanceframeworkbackend.repository.keycloak.UserRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository repository;
    private final UserConverter converter;

    public List<UserDto> findAllUsers() {
        return repository.findAll().stream().map(converter::userEntityToUserDto).toList();
    }

    public UserDto findById(String id) {
        return repository.findById(id).stream().map(converter::userEntityToUserDto).findFirst().orElse(null);
    }
}

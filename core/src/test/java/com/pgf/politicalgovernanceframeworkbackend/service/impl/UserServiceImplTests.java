package com.pgf.politicalgovernanceframeworkbackend.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;

import com.pgf.politicalgovernanceframeworkbackend.converter.UserConverter;
import com.pgf.politicalgovernanceframeworkbackend.dto.UserDto;
import com.pgf.politicalgovernanceframeworkbackend.entity.keycloak.UserEntity;
import com.pgf.politicalgovernanceframeworkbackend.exception.NotFoundException;
import com.pgf.politicalgovernanceframeworkbackend.repository.keycloak.UserRepository;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTests {
    @Mock
    private UserRepository repository;
    @Mock
    private UserConverter converter;
    @InjectMocks
    private UserServiceImpl service;

    @Test
    void shouldFindUserById() {
        // GIVEN
        String userId = "123";
        UserEntity userEntity = new UserEntity();
        UserDto expectedUserDto = UserDto.builder().build();
        given(repository.findById(userId)).willReturn(Optional.of(userEntity));
        given(converter.userEntityToUserDto(userEntity)).willReturn(expectedUserDto);

        // WHEN
        UserDto result = service.findById(userId);

        // THEN
        assertThat(result).isEqualTo(expectedUserDto);
    }

    @Test
    void shouldThrowExceptionWhenUserNotFound() {
        // GIVEN
        String userId = "123";

        // WHEN
        given(repository.findById(userId)).willReturn(Optional.empty());

        // THEN
        assertThatThrownBy(() -> service.findById(userId))
            .isInstanceOf(NotFoundException.class)
            .hasMessage("User not found");
    }

}

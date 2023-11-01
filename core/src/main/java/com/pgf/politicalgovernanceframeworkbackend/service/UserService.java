package com.pgf.politicalgovernanceframeworkbackend.service;

import com.pgf.politicalgovernanceframeworkbackend.dto.UserDto;

public interface UserService {
    UserDto findById(String id);
}

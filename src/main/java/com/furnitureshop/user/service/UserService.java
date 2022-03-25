package com.furnitureshop.user.service;

import com.furnitureshop.user.dto.CreateUserDto;
import com.furnitureshop.user.dto.UserDto;
import com.furnitureshop.user.entity.User;

import java.util.List;

public interface UserService {

    List<UserDto> findAllDto();

    boolean isTakenUsername(String username);

    boolean isTakenEmail(String email);

    User createUser(CreateUserDto dto);

}

package com.furnitureshop.user.service;

import com.furnitureshop.user.dto.ChangePasswordDto;
import com.furnitureshop.user.dto.CreateUserDto;
import com.furnitureshop.user.dto.UpdateUserDto;
import com.furnitureshop.user.entity.User;

import java.util.List;

public interface UserService {


    boolean isTakenUsername(String username);

    boolean isTakenEmail(String email);

    User createUser(CreateUserDto dto);

    List<User> getUsers();

    List<User> getCustomers();

    void deleteUserByUsername(String username);

    User getUserByUsername(String username);

    User updateUser(UpdateUserDto dto);

    User getProfile();

    boolean blockUser(String username);

    void changePassword(ChangePasswordDto dto);

    void deleteByUsername(String username);
}

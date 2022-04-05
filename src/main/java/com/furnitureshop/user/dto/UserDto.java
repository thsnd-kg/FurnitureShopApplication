package com.furnitureshop.user.dto;

import com.furnitureshop.role.entity.Group;

import java.util.Set;

public interface UserDto {
    String getUsername();

    String getEmail();

    Set<Group> getGroups();
}

package com.furnitureshop.user.dto;

import com.furnitureshop.role.entity.Group;

import java.util.Set;

public interface UserDto {
    public String getUsername();

    public String getEmail();

    public Set<Group> getGroups();
}

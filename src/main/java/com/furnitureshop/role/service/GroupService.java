package com.furnitureshop.role.service;


import com.furnitureshop.role.dto.AddRoleDto;
import com.furnitureshop.role.dto.AddUserDto;
import com.furnitureshop.role.dto.CreateGroupDto;
import com.furnitureshop.role.dto.GroupDto;
import com.furnitureshop.role.entity.Group;

import java.util.List;

public interface GroupService {

	boolean isTakenName(String groupName);

	List<GroupDto> findAll();

	Group add(CreateGroupDto dto);

	boolean isExisted(Long groupId);

	Group addRole(AddRoleDto dto);

	Group addUser(AddUserDto dto);

}

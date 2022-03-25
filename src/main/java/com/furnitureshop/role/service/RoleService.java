package com.furnitureshop.role.service;

import com.furnitureshop.role.dto.CreateRoleDto;
import com.furnitureshop.role.dto.RoleDto;
import com.furnitureshop.role.dto.UpdateRoleDto;
import com.furnitureshop.role.entity.Role;

import java.util.List;

// abstraction
public interface RoleService {
	// contract
	List<RoleDto> findAll();

	Role addNewRole(CreateRoleDto dto);

	boolean isTakenName(String roleName);

	boolean isExistedId(Long roleId);

	Role update(UpdateRoleDto dto, Long id);

	void deleteById(Long roleId);
}

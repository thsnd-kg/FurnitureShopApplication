package com.furnitureshop.role.controller;
import com.furnitureshop.common.ResponseHandler;
import com.furnitureshop.role.dto.CreateRoleDto;
import com.furnitureshop.role.dto.RoleDto;
import com.furnitureshop.role.dto.UpdateRoleDto;
import com.furnitureshop.role.entity.Role;
import com.furnitureshop.role.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/role")
public class RoleController {
	private  final RoleService service;

	@Autowired
	public RoleController(RoleService roleService) {
		service = roleService;
	}
	
	@GetMapping
	public Object findAllRole() {
		List<RoleDto> roles = service.findAll();
		
		return ResponseHandler.getResponse(roles, HttpStatus.OK);
	}
	
	@PostMapping
	public Object saveRole(@Valid @RequestBody CreateRoleDto dto, BindingResult errors) {
		if(errors.hasErrors())
			return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);
		
		Role addedRole = service.addNewRole(dto);
		
		return ResponseHandler.getResponse(addedRole, HttpStatus.CREATED);
	}
	
	
	@PutMapping
	public Object updateRole(@Valid @RequestBody UpdateRoleDto dto,
			BindingResult errors) {
		if(errors.hasErrors())
			return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);
		
		Role updatedRole = service.update(dto, dto.getId());
		return ResponseHandler.getResponse(updatedRole, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{role-id}")
	public Object deleteRole(@PathVariable("role-id") Long roleId) {
		service.deleteById(roleId);
		
		return ResponseHandler.getResponse(HttpStatus.OK);
	}
	
}

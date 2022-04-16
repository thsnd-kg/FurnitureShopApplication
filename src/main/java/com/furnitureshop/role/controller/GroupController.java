package com.furnitureshop.role.controller;

import com.furnitureshop.common.ResponseHandler;
import com.furnitureshop.role.dto.AddRoleDto;
import com.furnitureshop.role.dto.AddUserDto;
import com.furnitureshop.role.dto.CreateGroupDto;
import com.furnitureshop.role.dto.GroupDto;
import com.furnitureshop.role.entity.Group;
import com.furnitureshop.role.service.GroupService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/group")
public class GroupController {
	private final GroupService service;

    public GroupController(GroupService groupService) {
		service = groupService;
	}
	
	@GetMapping
	public Object findAllGroup() {
		List<GroupDto> groups = service.findAll();
		
		return ResponseHandler.getResponse(groups, HttpStatus.OK);
	}
	
	@PostMapping
	public Object createGroup(@Valid @RequestBody CreateGroupDto dto,
			BindingResult errors) {
		if(errors.hasErrors())
			return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);
		
		Group newGroup = service.add(dto);
		return ResponseHandler.getResponse(newGroup, HttpStatus.OK);
	}
	
	@PutMapping
	public Object updateGroup() {
		return null;
	}
	
	@PostMapping("/add-role")
	public Object addRoleToGroup(@Valid @RequestBody AddRoleDto dto, BindingResult errors) {
		if(errors.hasErrors())
			return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);
		
		Group updatedGroup = service.addRole(dto);
		
		return ResponseHandler.getResponse(updatedGroup, HttpStatus.OK);
	}
	
	@PostMapping("/remove-role")
	public Object removeRoleFromGroup() {
		return null;
	}
	
	@PostMapping("/add-user")
	public Object addUserToGroup(@Valid @RequestBody AddUserDto dto, BindingResult errors) {
		if(errors.hasErrors())
			return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);
		
		Group updatedGroup = service.addUser(dto);
		
		return ResponseHandler.getResponse(updatedGroup, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public Object deleteGroup(@PathVariable("id") Long groupId) {
		return null;
	}
}

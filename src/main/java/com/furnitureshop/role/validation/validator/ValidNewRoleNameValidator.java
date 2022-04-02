package com.furnitureshop.role.validation.validator;

import com.furnitureshop.common.util.ValidatorUtils;
import com.furnitureshop.role.dto.UpdateRoleDto;
import com.furnitureshop.role.service.RoleService;
import com.furnitureshop.role.validation.annotation.ValidNewRoleName;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidNewRoleNameValidator implements ConstraintValidator<ValidNewRoleName, UpdateRoleDto> {
	private String message;
	private final RoleService service;
	
	public ValidNewRoleNameValidator(RoleService roleService) {
		service = roleService;
	}
	
	@Override
	public void initialize(ValidNewRoleName constraintAnnotation) {
		message = constraintAnnotation.message();
	}
	
	@Override
	public boolean isValid(UpdateRoleDto dto, ConstraintValidatorContext context) {
		if(dto.getName().equals(dto.getOldName())) {
			boolean isTaken = service.isTakenName(dto.getName());
			
			if(!isTaken)
				return true;
		}
		
		ValidatorUtils.addError(context, message);
		return false;
	}

}

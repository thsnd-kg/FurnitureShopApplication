package com.furnitureshop.role.service;
import com.furnitureshop.role.dto.CreateRoleDto;
import com.furnitureshop.role.dto.RoleDto;
import com.furnitureshop.role.dto.UpdateRoleDto;
import com.furnitureshop.role.entity.Role;
import com.furnitureshop.role.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// concrete
@Service
public class RoleServiceImpl implements RoleService {
	private RoleRepository repository;

	RoleServiceImpl(RoleRepository repository){
		this.repository = repository;
	}

	
	@Override
	public List<RoleDto> findAll() {
		return repository.findAllDto();
	}

	@Override
	public Role addNewRole(CreateRoleDto dto) {
		Role newRole = new Role();
		
		newRole.setName(dto.getName().toUpperCase());
		newRole.setDescription(dto.getDescription());
		
		return repository.save(newRole);
	}

	@Override
	public boolean isTakenName(String roleName) {
		return repository.countByName(roleName.toUpperCase()) >= 1;
	}

	@Override
	public Role getRoleById(Long roleId) {
		Optional<Role> role = repository.findById(roleId);

		if(!role.isPresent())
			throw new IllegalStateException("Role does not exists");

		return role.get();
	}

	@Override
	public Role update(UpdateRoleDto dto, Long id) {
		Role role = repository.findById(id).get();
		
		role.setName(dto.getName().toUpperCase()); // add uppercase
		role.setDescription(dto.getDescription());
		
		return repository.save(role);
	}

	@Override
	public void deleteById(Long roleId) {
		repository.deleteById(roleId);;
	}

	@Override
	public boolean isExistedId(Long roleId) {
		return repository.existsById(roleId);
	}

}

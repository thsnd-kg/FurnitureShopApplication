package com.furnitureshop.role.repository;

import com.furnitureshop.role.dto.RoleDto;
import com.furnitureshop.role.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

	@Query("SELECT r FROM Role r WHERE r.activeFlag = 'Y'") // JPQL - Java Persistence Query Language
	List<RoleDto> findAllDto();

	int countByName(String roleName);
}

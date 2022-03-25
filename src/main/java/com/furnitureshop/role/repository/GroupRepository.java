package com.furnitureshop.role.repository;


import com.furnitureshop.role.dto.GroupDto;
import com.furnitureshop.role.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {

	int countByName(String groupName);

	@Query("SELECT g FROM Group g")
	List<GroupDto> findAllDto();

}

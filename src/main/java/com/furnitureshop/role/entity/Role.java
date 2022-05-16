package com.furnitureshop.role.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.furnitureshop.common.entity.BaseEntity;
import com.furnitureshop.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "furnitureshop_role")
public class Role extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "role_id", updatable = false)
	private Long roleId;

	private String name;
	
	private String description;

	@Column(columnDefinition = "varchar(1) default 'Y'")
	private String activeFlag = "Y";

	@JsonIgnore
	@OneToMany(mappedBy = "role")
	private Collection<User> users;

}

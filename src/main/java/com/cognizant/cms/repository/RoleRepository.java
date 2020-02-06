package com.cognizant.cms.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cognizant.cms.model.Role;

public interface RoleRepository extends MongoRepository<Role, String> {

	Role findFirstByroleName(String roleName);
	
	
}

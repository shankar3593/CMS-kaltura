package com.cognizant.cms.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.cognizant.cms.model.RoleView;

public interface RoleViewRepository extends MongoRepository<RoleView, String>{

	RoleView findFirstByroleName(String roleName);
}


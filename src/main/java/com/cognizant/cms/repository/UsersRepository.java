package com.cognizant.cms.repository;

import org.springframework.data.mongodb.repository.MongoRepository;


import com.cognizant.cms.model.Users;

public interface UsersRepository extends MongoRepository<Users, String> {
	
	 public Users findByUsername(String username);

}

package com.cognizant.cms.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.cognizant.cms.model.User;

@CrossOrigin
public interface UserRepository extends MongoRepository<User, String> {

	User findFirstByUserName(String userName);
	
	 @Query("{'roles.roleName': ?0}")
	List<User> findRoles(String roleName); 

}

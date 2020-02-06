package com.cognizant.cms.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.cognizant.cms.model.SiteInfo;

public interface SiteInfoRepository extends MongoRepository<SiteInfo, String> {
	
	SiteInfo findFirstByName(String name);
	
	@Query(value = "{'name': ?0}")
	public SiteInfo findAllName();

	
}

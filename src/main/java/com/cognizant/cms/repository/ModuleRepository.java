package com.cognizant.cms.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.cognizant.cms.model.Module;
public interface ModuleRepository extends MongoRepository<Module, String> {

}

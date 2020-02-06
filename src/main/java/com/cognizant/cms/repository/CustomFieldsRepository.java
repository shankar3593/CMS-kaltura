package com.cognizant.cms.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import com.cognizant.cms.model.CustomField;


public interface CustomFieldsRepository extends MongoRepository<CustomField,String> {
	
	CustomField findFirstByName(String name);
	
	Optional<CustomField> findById(String id);
	
	List<CustomField> findByClassName(String classname);
	
//	@Query(value = "{'asset_ID': ?0, 'className' : ?1}")
//	public List<CustomField> findByassetidclass(String asset_ID,String classname);
	@Query(value = "{'title_id': ?0, 'className' : ?1}")
	public List<CustomField> findBytitleidclass(String title_id,String classname);
	
}

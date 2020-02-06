package com.cognizant.cms.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import com.cognizant.cms.model.App_Data;
import com.cognizant.cms.model.Metadata;

public interface MetadataRepo extends MongoRepository<Metadata, String> {

//	@Query(value = "{'ams.asset_ID': ?0, 'ams.asset_Class' : ?1}")
//	public List<Metadata> findByassetidclass(String asset_ID,String classname);
//	
//	@Query(value = "{'ams.asset_ID': ?0, 'ams.asset_Class' : ?1}")
//	public List<App_Data> findApp_DatasByassetidclass(String asset_ID,String classname);
//	
//	@Query(value = "{'ams.asset_ID': ?0, 'ams.asset_Class' : ?1, 'app_Data.name' : ?2}")
//	public List<App_Data> findappKey(String asset_ID,String classname,String key);
//	
//	@Query(value = "{'ams.asset_ID': ?0, 'ams.asset_Class' : ?1, 'app_Data.name' : ?2}")
//	public List<Metadata> deleteApp_Data(String asset_ID,String classname,String key);
	
	@Query(value = "{'title_id': ?0, 'ams.asset_Class' : ?1}")
	public List<Metadata> findBytitleidclass(String title_id,String classname);
	
	@Query(value = "{'title_id': ?0, 'ams.asset_Class' : ?1}")
	public List<App_Data> findApp_DatasBytitleidclass(String title_id,String classname);
	
	@Query(value = "{'title_id': ?0, 'ams.asset_Class' : ?1, 'app_Data.name' : ?2}")
	public List<App_Data> findappKey(String title_id,String classname,String key);
	
	@Query(value = "{'title_id': ?0, 'ams.asset_Class' : ?1, 'app_Data.name' : ?2}")
	public List<Metadata> deleteApp_Data(String title_id,String classname,String key);
	
	@Query(value = "{'ams.asset_Class' : ?0}")
	public List<Metadata> findAllAdi(String classname);
	
	@Query(value = "{'title_id': ?0}")
	public List<Metadata> findBytitleid(String title_id);
	


}

package com.cognizant.cms.controller;

import java.util.LinkedList;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//import javax.persistence.criteria.CriteriaBuilder.In;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.cms.configuration.Mongo;
import com.cognizant.cms.model.AMS;
import com.cognizant.cms.model.App_Data;
import com.cognizant.cms.model.CustomField;
import com.cognizant.cms.model.Metadata;
import com.cognizant.cms.repository.CustomFieldsRepository;
import com.cognizant.cms.repository.MetadataRepo;


@RestController
@RequestMapping("/app_data")
public class MetadataController {
	
	private static final Logger logger = LoggerFactory.getLogger(MetadataController.class);


	@Autowired
	CustomFieldsRepository cf_Repository;

	@Autowired
	MetadataRepo meta_repo;

	@Autowired
	MongoOperations mongoOperations;

	@RequestMapping(value = "/save_cf", method = RequestMethod.POST)
	@ResponseBody
	public List<CustomField> saveCustomField(@RequestBody List<CustomField> customFields, String classname,
			String title_id) {

		logger.info("Create CustomField (Save cf to customField db as well as Metadata db) API Used ");

		List<Metadata> metadatas = null;
		List<CustomField> cff = null;

		try {

			for (CustomField customField : customFields) {
				customField.setClassName(classname);
				//customField.setAsset_ID(asset_ID);
				customField.setTitle_id(title_id);
				System.out.println(customField);
			}

			cf_Repository.saveAll(customFields);
			List<App_Data> app_Datas = new LinkedList<App_Data>();
			for (CustomField customField : customFields) {
				App_Data app_Data = new App_Data();
				app_Data.setName(customField.getName());
				app_Data.setValue(null);
				app_Datas.add(app_Data);

			}

			for (App_Data app : app_Datas) {
				System.out.println(app.getName());
			}

			metadatas = meta_repo.findBytitleidclass(title_id, classname);

			List<App_Data> ap_Datas = metadatas.get(0).getApp_Data();

			ap_Datas.addAll(app_Datas);

			Update update = new Update();
			update.set("app_Data", ap_Datas);
			Query query = new Query();
			query.addCriteria(Criteria.where("ams.asset_Class").is(classname)
					.andOperator(Criteria.where("title_id").is(title_id)));
			Mongo.mongoTemplate().updateMulti(query, update, Metadata.class);

			Query q = new Query();
			q.addCriteria(Criteria.where("title_id").is(title_id).andOperator(Criteria.where("className").is(classname)));
			cff = Mongo.mongoTemplate().find(q, CustomField.class);



		} catch (Exception e) {

			e.printStackTrace();

		}
		return cff;

	}



	@RequestMapping(value = "/fetch_cf_classname", method = RequestMethod.GET)
	public List<CustomField> getCustomFieldsUsingClass(String classname) throws Exception {
		
		logger.info("Fetch custom fields using classname API Used ");
		List<CustomField> fields = cf_Repository.findByClassName(classname);
		logger.info("Exit : Customfields using classname");
		return fields;
		
	}

	@RequestMapping(value = "/fetchClassMetadata", method = RequestMethod.GET)
	public List<Metadata> fetchMetadata(String classname, String id) {

		logger.info("Fetch ClassMetadaa API Used ");
		Query query = new Query();
		query.addCriteria(
				Criteria.where("ams.asset_Class").is(classname).andOperator(Criteria.where("ams.asset_ID").is(id)));
		List<Metadata> metadatas = Mongo.mongoTemplate().find(query, Metadata.class);
		logger.info("Exit : ClassMetadata");
		return metadatas;

	}
	
	@RequestMapping(value = "/fetchbytitleid", method=RequestMethod.GET)
	public List<Metadata> fetchbyitleid(String title_id){
		Query query=new Query();
		query.addCriteria(Criteria.where("title_id").is(title_id));
		List<Metadata> metadata = Mongo.mongoTemplate().find(query, Metadata.class);
		System.out.println(metadata);
		return metadata;
	}
	

	@RequestMapping(value = "/updatecf/{id}/{classname}", method = RequestMethod.PUT)
	public List<CustomField> modifycfById(@PathVariable String id, @PathVariable String classname,
			@RequestBody CustomField cfs) {
		
		logger.info("Update customfields using id and classname API Used ");
		Query query = new Query();
		// Query query1=new Query();
		Update update = new Update();
		query.addCriteria(Criteria.where("id").is(id));
		update.set("displayname", cfs.getDisplayname());
		update.set("className", cfs.getClassName());
		update.set("datatype", cfs.getDatatype());
		update.set("fieldtype", cfs.getFieldtype());
		update.set("name", cfs.getName());
		Mongo.mongoTemplate().updateMulti(query, update, CustomField.class);
		logger.info("Exit : Update customfields using id and classname");
		return cf_Repository.findByClassName(classname);

	}

	
	@RequestMapping(value = "/updatemetadata/{title_id}/{classname}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public Metadata modifymd(@PathVariable String title_id, @PathVariable String classname, @RequestBody Metadata md) {

		logger.info("Update Metadata API Used ");
		Query query = new Query();
		query.addCriteria(Criteria.where("ams.asset_Class").is(classname)
				.andOperator(Criteria.where("title_id").is(title_id)));


		Update update = new Update();
		AMS am = md.getAms();
		//md.setTitle_id(title_id);
		System.out.println(am);
		if (am.getVersion_Major() != null)
			update.set("ams.version_Major", am.getVersion_Major());
		if (am.getVersion_Minor() != null)
			update.set("ams.version_Minor",Integer.toString((Integer.parseInt(am.getVersion_Minor())+1)));
		if (am.getProvider_ID() != null)
			update.set("ams.provider_ID", am.getProvider_ID());
		if (am.getProvider() != null)
			update.set("ams.provider", am.getProvider());
		if (am.getProduct() != null)
			update.set("ams.product", am.getProduct());
		if (am.getDescription() != null)
			update.set("ams.description", am.getDescription());
		if (am.getCreation_Date() != null)
			update.set("ams.creation_Date", am.getCreation_Date());
		if (am.getAsset_Name() != null)
			update.set("ams.asset_Name", am.getAsset_Name());
		if (am.getAsset_ID() != null)
			update.set("ams.asset_ID", am.getAsset_ID());
		if (am.getAsset_Class() != null)
			update.set("ams.asset_Class", am.getAsset_Class());

		//List<App_Data> app_Datas = md.getApp_Data();

		Mongo.mongoTemplate().updateMulti(query, update, Metadata.class);

		List<App_Data> datas = md.getApp_Data();

		String name;
		for (App_Data app_Data : datas) {
			if (app_Data.getValue() != null) {
				name = app_Data.getName();
				//		//		q.addCriteria(Criteria.where("ams.asset_Class").is(classname).
				//		//				andOperator(Criteria.where("ams.asset_ID").is(id)));
				//				query.fields().include("name").elemMatch("app_Data", Criteria.where("name").
				//						is(name) );
				//				List<Metadata> met=Mongo.mongoTemplate().find(query, Metadata.class);
				//				update.set("app_Data.$.value",app_Data.getValue());
				//				System.out.println(met.get(0));
				//			//	update.addToSet("app_Data.value", app_Data.getValue());
				//				Mongo.mongoTemplate().updateFirst(query, update, Metadata.class);


				//				Query query1 = new Query(new Criteria().andOperator(
				//						Criteria.where("ams.asset_Class").is(classname)
				//								.andOperator(Criteria.where("ams.asset_ID").is(asset_id)),
				//						Criteria.where("app_Data").elemMatch(Criteria.where("name").is(name))));
				//				update.set("app_Data.$.value", app_Data.getValue());
				//				Mongo.mongoTemplate().updateFirst(query1, update, Metadata.class);
				Query query1 = new Query(new Criteria().andOperator(
						Criteria.where("ams.asset_Class").is(classname)
						.andOperator(Criteria.where("title_id").is(title_id)),
						Criteria.where("app_Data").elemMatch(Criteria.where("name").is(name))));
				update.set("app_Data.$.value", app_Data.getValue());
				Mongo.mongoTemplate().updateFirst(query1, update, Metadata.class);


			}
		}
		
		Metadata metadata = meta_repo.findBytitleidclass(title_id, classname).get(0);
		logger.info("Exit : Update Metadata");

		return metadata;

	}

}

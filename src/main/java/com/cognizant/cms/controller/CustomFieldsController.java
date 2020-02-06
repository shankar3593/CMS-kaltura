package com.cognizant.cms.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.cms.configuration.Mongo;
import com.cognizant.cms.model.App_Data;
import com.cognizant.cms.model.Metadata;
import com.cognizant.cms.model.CustomField;
import com.cognizant.cms.repository.CustomFieldsRepository;
import com.cognizant.cms.repository.MetadataRepo;

@RestController
@RequestMapping("/cf")
public class CustomFieldsController {
	
	private static final Logger logger = LoggerFactory.getLogger(CustomFieldsController.class);


	@Autowired
	CustomFieldsRepository cf_repository;

	@Autowired
	MetadataRepo repo;

//	@RequestMapping(value = "/all", method = RequestMethod.GET)
//	public List<CustomField> getAllCustomFields() throws Exception {
//		JAXBContext contextObj=JAXBContext.newInstance(CustomField.class);
//		Marshaller marshallerObj = contextObj.createMarshaller();  
//		marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//		List<CustomField> fields=cf_repository.findAll();
//		CustomField cf;
//		for (CustomField customFields : fields) {
//			cf=new CustomField(customFields.getName(),customFields.getDisplayname() ,customFields.getFieldtype() , customFields.getDatatype(),customFields.getIdd(), customFields.getClassName());
//		    //marshallerObj.marshal(customFields, new FileOutputStream("customfields.xml"));
//		    //customFields.setId(customFields.getId()+1);
//		    marshallerObj.marshal(cf, new FileOutputStream("customfields.xml"));
//		}
//		
//		//CustomFields cf1 =new CustomFields();
//		
//			
//		//marshallerObj.marshal(cf_repository.findAll(), new FileOutputStream("customfields1.xml"));
//		return cf_repository.findAll();
//	}
////	
//	@RequestMapping(value = "/fetch_cf_classname", method = RequestMethod.GET)
//	public List<CustomField> getCustomFieldsUsingClass(String classname) throws Exception {
//		
//		List<CustomField> fields=cf_repository.findByClassName(classname);
//		return fields;
//	}

	@RequestMapping(value = "/createcf", method = RequestMethod.POST)
	public CustomField createcf(@RequestBody CustomField customFields) {
		logger.info("Create CustomField API Used ");
		logger.info("Enter : Create CustomField {}", customFields.getName());
		return cf_repository.save(customFields);
		
	}

	@RequestMapping(value = "/deleteCustomFields/{name}", method = RequestMethod.DELETE)
	public List<CustomField> deleteCustomFields(@PathVariable String name) {
		logger.info("Delete CustomField API Used");
		logger.info("Deleted data :- " + name);
		CustomField cf = cf_repository.findFirstByName(name);
		cf_repository.delete(cf);
		logger.info("Exit : DeleteCustomField");
		return cf_repository.findAll();

	}



	@RequestMapping(value = "/deletecf/{title_id}/{classname}/{id}", method = RequestMethod.DELETE)
	public List<CustomField> deleteCustomFieldss(@PathVariable String title_id, @PathVariable String classname,
			@PathVariable String id) {

		logger.info("Delete from CustomField db API Used ");
		String name = cf_repository.findById(id).get().getName();
		Query query = new Query();
		query.addCriteria(Criteria.where("className").is(classname).andOperator(Criteria.where("id").is(id)));
		List<CustomField> cfs = Mongo.mongoTemplate().find(query, CustomField.class);

		System.out.println(name);
		System.out.println(cfs);
		cf_repository.delete(cfs.get(0));
		logger.info("Exit : DeleteCustomField from customfield db");

		
		logger.info("Delete CustomField from Metadata db API Used ");
		Query query1 = new Query();
		query1.addCriteria(Criteria.where("ams.asset_Class").is(classname)
				.andOperator(Criteria.where("title_id").is(title_id)));
		List<Metadata> metadatas = repo.findBytitleidclass(title_id, classname);

		App_Data data = new App_Data();
		
		System.out.println(name);
		data.setName(name);
		List<App_Data> app_Datas = metadatas.get(0).getApp_Data();
		
		System.out.println(app_Datas);
		app_Datas.remove(data);
		System.out.println(app_Datas);
		Update update = new Update().set("app_Data", app_Datas);
		Mongo.mongoTemplate().updateFirst(query1, update, Metadata.class);
		logger.info("Exit : DeleteCustomField from Metadata db");

		
//		Query query1 =new Query(new Criteria().andOperator(
//				  Criteria.where("ams.asset_Class").is(classname).andOperator(Criteria.where("ams.asset_ID").is(id)),
//				  Criteria.where("app_Data").elemMatch(Criteria.where("name").is(name))
//				));
//		Update update=new Update();
//		
//		update.unset("app_Data.$");
//		Mongo.mongoTemplate().updateFirst(query1, update, Metadata.class);
		
	
//		
//		return cf_repository.findByassetidclass(asset_id, classname);


		return cf_repository.findBytitleidclass(title_id, classname);
	}



}

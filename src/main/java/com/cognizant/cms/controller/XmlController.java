package com.cognizant.cms.controller;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.cms.configuration.Mongo;
import com.cognizant.cms.model.ADI;
import com.cognizant.cms.model.AMS;
import com.cognizant.cms.model.App_Data;
import com.cognizant.cms.model.Asset;
import com.cognizant.cms.model.Metadata;
import com.cognizant.cms.normalization.Rule1;
import com.cognizant.cms.normalization.Rule2;
import com.cognizant.cms.parser.XmlParsing;
import com.cognizant.cms.repository.ContentRepo;
import com.cognizant.cms.repository.MetadataRepo;

import com.cognizant.cms.validator.ReasonContent;
import com.cognizant.cms.validator.ReasonProvider;
import com.cognizant.cms.validator.ValidateContent;
import com.cognizant.cms.validator.ValidateMajorWithinADI;
import com.cognizant.cms.validator.ValidateProvider;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

@RestController
@RequestMapping("parsing_adi_data")
public class XmlController {

	@Autowired
	MetadataRepo repo;

	@Autowired
	ContentRepo repo1;

	@RequestMapping(value = "/getAssetData", method = RequestMethod.GET, produces = "application/json")
	public List<Metadata> getOverview() {
		
		List<Metadata> adisRecord = repo.findAllAdi("package");
		return adisRecord;

	}

	@RequestMapping("/save_metadata")
	public String saveMetadata() throws Exception {
		Rule1 rule1 = new Rule1();
		Rule2 rule2 = new Rule2();
		XmlParsing parsing = new XmlParsing();
		ADI adi = parsing.getData();
		
		
		ValidateMajorWithinADI.validateMajorWithinADI(adi);
		String title_id = parsing.generateTitleId();
		Metadata metadata = null;

		metadata = adi.getMetadata();
		metadata.setTitle_id(title_id);
		
		metadata.setStatus(ValidateProvider.validateProviedr(metadata.getAms().getProvider_ID()));
	
		metadata.setReason(ReasonProvider.reasonProviedr(metadata.getAms().getProvider_ID()));
		
		rule1.normalization_rule(title_id);
		rule2.normalization_rule2(title_id);
		repo.save(metadata);

		List<Asset> assets = adi.getAsset();
		for (Asset asset : assets) {
		
			metadata = asset.getMetadata();
			metadata.setTitle_id(title_id);
		
			metadata.setStatus(ValidateProvider.validateProviedr(metadata.getAms().getProvider_ID()));
			
			metadata.setReason(ReasonProvider.reasonProviedr(metadata.getAms().getProvider_ID()));
			repo.save(metadata);

			List<Asset> nestedassets = asset.getAsset();
			for (Asset asset2 : nestedassets) {
				metadata = asset2.getMetadata();
				metadata.setTitle_id(title_id);
				metadata.setStatus(ValidateProvider.validateProviedr(metadata.getAms().getProvider_ID()));
				metadata.setReason(ReasonProvider.reasonProviedr(metadata.getAms().getProvider_ID()));
				metadata.setContentType(asset2.getContent());
				metadata.setStatus(ValidateContent.validateContent(metadata.getContentType().getValue()));
				metadata.setReason(ReasonContent.reasonContent(metadata.getContentType().getValue()));
				repo.save(metadata);
			}
		}
		return "adi has been parsed and saved";
	}

	@RequestMapping(value = "/fetch_metadata", method = RequestMethod.GET)
	public List<Metadata> fetchMetadata(String id, String classname) {

		Query query = new Query();
		query.addCriteria(
				Criteria.where("ams.asset_Class").is(classname).andOperator(Criteria.where("ams.asset_ID").is(id)));
		List<Metadata> metadatas = Mongo.mongoTemplate().find(query, Metadata.class);

		return metadatas;

	}

	@RequestMapping(value = "/fetch_appdata", method = RequestMethod.GET)
	public List<String> fetchAppdata(String id, String classname) {

		Query query = new Query();
		query.addCriteria(
				Criteria.where("ams.asset_Class").is(classname).andOperator(Criteria.where("ams.asset_ID").is(id)));
		List<Metadata> metadatas = Mongo.mongoTemplate().find(query, Metadata.class);

		List<App_Data> app_Datas = metadatas.get(0).getApp_Data();
		List<String> names = new LinkedList<String>();
		for (App_Data app_Data : app_Datas) {
			names.add(app_Data.getName());
		}
		return names;
	}

	@RequestMapping(value = "/fetch_sorted_metadata", method = RequestMethod.GET)
	public List<Metadata> fetchSortedMetadata() {

		Query query = new Query();

		query = new Query();
		List<Metadata> metadatas = Mongo.mongoTemplate()
				.find(query.with(new Sort(new Order(Direction.ASC, "ams.creation_time"))), Metadata.class);
		// Mongo.mongoTemplate.find(query, Animal.class);

		return metadatas;
	}

	@RequestMapping(value = "/fetch_latest_metadata", method = RequestMethod.GET)
	public List<Metadata> fetchLatestMetadata() {

		Query query = new Query();

		query = new Query();
		List<Metadata> metadatas = Mongo.mongoTemplate()
				.find(query.with(new Sort(new Order(Direction.DESC, "ams.creation_time"))), Metadata.class);
		// Mongo.mongoTemplate.find(query, Animal.class);

		return metadatas;
	}

	public void fetch_major_Version(int new_version, String titleId) {

		int diff = new_version - 1;
		Query query = new Query();
		Metadata md;
		query.addCriteria(Criteria.where("ams.asset_Class").is("package")
				.andOperator(Criteria.where("ams.asset_ID").is("MCHP3200000000049305")));
		List<Metadata> metadatas = Mongo.mongoTemplate().find(query, Metadata.class);

		String version = metadatas.get(0).getAms().getVersion_Major();
		int res = Integer.parseInt(version);
		// System.out.println(version);

		if (res == diff)
			System.out.println("Pass");
		else
			System.out.println("Fail");

	}
}

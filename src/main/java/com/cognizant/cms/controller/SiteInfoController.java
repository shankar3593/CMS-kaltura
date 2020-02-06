package com.cognizant.cms.controller;

import java.util.List;

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
import com.cognizant.cms.model.Metadata;
import com.cognizant.cms.model.SiteInfo;
import com.cognizant.cms.model.SiteMetaData;
import com.cognizant.cms.parser.XmlParsing;
import com.cognizant.cms.repository.MetadataRepo;
import com.cognizant.cms.repository.SiteInfoRepository;
import com.cognizant.cms.repository.SiteMetaDataRepository;

@RestController
@RequestMapping("SiteInfo")
public class SiteInfoController {
	
	@Autowired
	SiteInfoRepository siteinforepository;
	
	@Autowired
	MetadataRepo meta_repo;
	
	@Autowired
	SiteMetaDataRepository sitemetadatarepository;
	
	
	@RequestMapping(value="/find", method=RequestMethod.GET)
	public List<SiteInfo> findall() {
		System.out.println("hello");
		return siteinforepository.findAll();
		
	}
	
	@RequestMapping(value="/findbyname/{name}", method=RequestMethod.GET)
	public SiteInfo findByName(@PathVariable String name){
		return siteinforepository.findFirstByName(name);
		
	}

	@RequestMapping(value="/save", method=RequestMethod.POST)
	public SiteInfo createsiteinfo(@RequestBody SiteInfo siteinfo) {
		XmlParsing parsing=new XmlParsing();
		String siteId=parsing.generateTitleId();
		siteinfo.setSiteId(siteId);
		return siteinforepository.save(siteinfo);
		
	}
	
	@RequestMapping(value="/delete/{name}", method=RequestMethod.DELETE)
	public List<SiteInfo> deletesiteinfo(@PathVariable String name) {
		System.out.println(name);
		SiteInfo siteinfo = siteinforepository.findFirstByName(name);
		System.out.println(siteinfo);
		siteinforepository.delete(siteinfo);
		return siteinforepository.findAll();
	}
	
	@RequestMapping(value="/sitelist", method=RequestMethod.GET)
	public List<Metadata> getsitelist(@PathVariable String title_id, String classname){
		Query query = new Query();
		query.addCriteria(
				Criteria.where("ams.asset_Class").is(classname).andOperator(Criteria.where("title_id").is(title_id)));
		List<Metadata> metadatas = Mongo.mongoTemplate().find(query, Metadata.class);
		return metadatas;
		
		 
	}
	
	@RequestMapping(value="/publish/{siteId}/{title_id}", method=RequestMethod.POST)
	public List<SiteMetaData> publishSite(@PathVariable String siteId, @PathVariable String title_id ){

		Query query=new Query();
		SiteMetaData sitemetadata=new SiteMetaData();
		//Metadata metadata = meta_repo.findBytitleidclass(title_id, classname).get(0);
		//Metadata metadata = meta_repo.findBytitleid(title_id).get(0);
		System.out.println(title_id);
		query.addCriteria(Criteria.where("title_id").is(title_id));
		List<Metadata> metadata = Mongo.mongoTemplate().find(query, Metadata.class);

		System.out.println(metadata);
		sitemetadata.setSiteId(siteId);
		sitemetadata.setMetadata(metadata);
		sitemetadatarepository.save(sitemetadata);
		return sitemetadatarepository.findAll();
		
		
	}
	
	
//	Query query=new Query();
//	query.addCriteria(Criteria.where("siteId").is(siteId).andOperator(Criteria.where("name").is(name)));
//	Update update = new Update();
//	Rule1 rule1=new Rule1();
//	rule1.normalization_rule(siteId);
//	Rule2 rule2=new Rule2();
//	rule2.normalization_rule2(siteId);
//	update.set("ams.version_Major", metadatas.getAms().getVersion_Major());
//	update.set("ams.version_Minor", metadatas.getAms().getVersion_Minor());
//	update.set("ams.provider_ID", metadatas.getAms().getProvider_ID());
//	update.set("ams.provider", metadatas.getAms().getProvider());
//	update.set("ams.product", metadatas.getAms().getProduct());
//	update.set("ams.description", metadatas.getAms().getDescription());
//	update.set("ams.creation_Date", metadatas.getAms().getCreation_Date());
//	update.set("ams.asset_Name", metadatas.getAms().getAsset_Name());
//	update.set("ams.asset_ID", metadatas.getAms().getAsset_ID());
//	update.set("ams.asset_Class", metadatas.getAms().getAsset_Class());
//	Mongo.mongoTemplate().updateMulti(query, update, SiteInfo.class);
//	sitemetadata.setSiteId(siteId);
//	sitemetadata.setMetadata(metadatas);
//	return siteinforepository.findAll();
	
	
	@RequestMapping(value = "/updatesiteinfo/{name}", method = RequestMethod.PUT)
	public SiteInfo modifyByName(@PathVariable String name, @RequestBody SiteInfo si){
		Query query=new Query();
		Update update=new Update();
		query.addCriteria(Criteria.where("name").is(name));
		update.set("siteType", si.getSiteType());
		update.set("externalId", si.getExternalId());
		update.set("description", si.getDescription());
		update.set("metadataFormat", si.getMetadataFormat());
		update.set("distributionOption", si.getDistributionOption());
		Mongo.mongoTemplate().updateMulti(query, update, SiteInfo.class);
		return siteinforepository.findFirstByName(name);
		
	}

}

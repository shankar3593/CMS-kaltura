package com.cognizant.cms.normalization;

import java.util.LinkedList;
import java.util.List;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.cognizant.cms.configuration.Mongo;
import com.cognizant.cms.model.Metadata;

public class Rule1 {

	public List<Metadata> normalization_rule(String id) {
		List<Metadata> adi=new LinkedList<Metadata>();
		Query query1 = new Query();
		Query query2 = new Query();
		Query query3 = new Query();
		Query query4 = new Query();
		query1.addCriteria(
		Criteria.where("ams.asset_Class").is("title").andOperator(Criteria.where("title_id").is(id)));
		List<Metadata> titlemetadata = Mongo.mongoTemplate().find(query1, Metadata.class);
		if(titlemetadata.get(0).getApp_Data().get(0).getName().equals("Rating") && titlemetadata.get(0).getApp_Data().get(0).getValue().equals("XXX"))
		{
		titlemetadata.get(0).getApp_Data().get(0).setValue("TV_MA");
		}
		if(titlemetadata.get(0).getApp_Data().get(0).getName().equals("Rating") && titlemetadata.get(0).getApp_Data().get(0).getValue().equals("NR_ADULT"))
		{
			titlemetadata.get(0).getApp_Data().get(0).setValue("TV_MA");

		}
		adi.add(titlemetadata.get(0));
		query2.addCriteria(
		Criteria.where("ams.asset_Class").is("poster").andOperator(Criteria.where("title_id").is(id)));
		List<Metadata> postermetadata = Mongo.mongoTemplate().find(query2, Metadata.class);
		adi.add(postermetadata.get(0));
		query3.addCriteria(
		Criteria.where("ams.asset_Class").is("package").andOperator(Criteria.where("title_id").is(id)));
		List<Metadata> packagemetadata = Mongo.mongoTemplate().find(query3, Metadata.class);
		adi.add(packagemetadata.get(0));
		query4.addCriteria(
		Criteria.where("ams.asset_Class").is("movie").andOperator(Criteria.where("title_id").is(id)));
		List<Metadata> moviemetadata = Mongo.mongoTemplate().find(query4, Metadata.class);
		adi.add(moviemetadata.get(0));
		return adi;
		}

}

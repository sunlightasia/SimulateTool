package com.enviseo.persistence;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.enviseo.model.Form;

@Repository
public class FormPersistence {
	@Autowired
	private MongoTemplate mongoTemplate;

	public static final String COLLECTION_NAME = "form";

	public void addForm(Form form) {
		if (!mongoTemplate.collectionExists(Form.class)) {
			mongoTemplate.createCollection(Form.class);
		}
		form.setId(UUID.randomUUID().toString());
		mongoTemplate.insert(form, COLLECTION_NAME);
	}

	public Form findOneByName(String name) {
		Query query = new Query();
		query.addCriteria(Criteria.where("name").is(name));
		return mongoTemplate.findOne(query, Form.class);
	}

	public Form findOneByNameBySiteId(String name, String siteId) {
		Query query = new Query();
		query.addCriteria(Criteria.where("name").is(name).and("siteId").is(siteId));
		return mongoTemplate.findOne(query, Form.class);
	}

	public List<Form> findBySiteId(String siteId) {
		Query query = new Query();
		query.addCriteria(Criteria.where("siteId").is(siteId));
		return mongoTemplate.find(query, Form.class);
	}

	public List<Form> listForm() {
		return mongoTemplate.findAll(Form.class, COLLECTION_NAME);
	}

	public void deleteForm(Form form) {
		mongoTemplate.remove(form, COLLECTION_NAME);
	}

	public void updateForm(Form form) {
		mongoTemplate.insert(form, COLLECTION_NAME);
	}
}

package com.enviseo.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.enviseo.model.Form;

@Repository
public class FormService {
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

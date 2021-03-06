package com.enviseo.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.enviseo.model.Field;

@Repository
public class FieldService {
	@Autowired
	private MongoTemplate mongoTemplate;

	public static final String COLLECTION_NAME = "field";

	public void addField(Field field) {
		if (!mongoTemplate.collectionExists(Field.class)) {
			mongoTemplate.createCollection(Field.class);
		}
		field.setId(UUID.randomUUID().toString());
		mongoTemplate.insert(field, COLLECTION_NAME);
	}

	public List<Field> listField() {
		return mongoTemplate.findAll(Field.class, COLLECTION_NAME);
	}

	public void deleteField(Field field) {
		mongoTemplate.remove(field, COLLECTION_NAME);
	}

	public void updateField(Field field) {
		mongoTemplate.insert(field, COLLECTION_NAME);
	}
}

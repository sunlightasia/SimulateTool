package com.enviseo.persistence;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.enviseo.model.Site;

@Repository
public class SitePersistence {
	@Autowired
	private MongoTemplate mongoTemplate;

	public static final String COLLECTION_NAME = "site";

	public void addSite(Site site) {
		if (!mongoTemplate.collectionExists(Site.class)) {
			mongoTemplate.createCollection(Site.class);
		}
		site.setId(UUID.randomUUID().toString());
		mongoTemplate.insert(site, COLLECTION_NAME);
	}

	public List<Site> listSite() {
		return mongoTemplate.findAll(Site.class, COLLECTION_NAME);
	}

	public void deleteSite(Site site) {
		mongoTemplate.remove(site, COLLECTION_NAME);
	}

	public void updateSite(Site site) {
		mongoTemplate.insert(site, COLLECTION_NAME);
	}
}

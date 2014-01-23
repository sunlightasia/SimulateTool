package com.enviseo.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.enviseo.model.Account;

@Repository
public class AccountService {
	@Autowired
	private MongoTemplate mongoTemplate;

	public static final String COLLECTION_NAME = "site";

	public void addAccount(Account site) {
		if (!mongoTemplate.collectionExists(Account.class)) {
			mongoTemplate.createCollection(Account.class);
		}
		site.setId(UUID.randomUUID().toString());
		mongoTemplate.insert(site, COLLECTION_NAME);
	}

	public List<Account> listAccount() {
		return mongoTemplate.findAll(Account.class, COLLECTION_NAME);
	}

	public void deleteAccount(Account account) {
		mongoTemplate.remove(account, COLLECTION_NAME);
	}

	public void updateAccount(Account account) {
		mongoTemplate.insert(account, COLLECTION_NAME);
	}
}

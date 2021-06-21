package com.kucukcinar.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.kucukcinar.models.AppUser;


@Repository
@Transactional(readOnly = true)
public interface AppUserRepository extends MongoRepository<AppUser, String>{
	Boolean findByEmail(String email);
	Optional<AppUser> findByUsername(String username);
}

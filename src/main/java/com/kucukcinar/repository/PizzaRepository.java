package com.kucukcinar.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;

import com.kucukcinar.models.Pizza;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface PizzaRepository extends MongoRepository<Pizza, String>{
	
	List<Pizza> findByPriceLessThan(int MaxPrice);
	
	@Query(value = "{ingredients:?0}")
	List<Pizza> findByIngredients(String ingredient);
}

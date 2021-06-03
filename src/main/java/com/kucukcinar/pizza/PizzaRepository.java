package com.kucukcinar.pizza;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
@Repository
public interface PizzaRepository extends MongoRepository<Pizza, String>{
	
	List<Pizza> findByPriceLessThan(int MaxPrice);
	
	@Query(value = "{ingredients:?0}")
	List<Pizza> findByIngredients(String ingredient);
}

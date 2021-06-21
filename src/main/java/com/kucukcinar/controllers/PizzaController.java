package com.kucukcinar.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kucukcinar.models.Pizza;
import com.kucukcinar.services.PizzaService;

/**
 * Rest Controller layer for Pizza Entities 
 * @author yigit
 * @version 0.1
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/pizzas")
public class PizzaController {

	@Autowired // dependency injection.
	private PizzaService pizzaService;

	/**
	 * this method used for getting all the pizza entities without any filtering.
	 * @return List of Pizzas
	 */
	@CrossOrigin(origins="http://localhost:8081")
	@GetMapping("/getAllPizzas")
	public List<Pizza> getAllPizzas() {
		return pizzaService.getAllPizzas();
	}

	/**
	 * this method used for getting the the pizza with the id.
	 * @param id - String, as saved in database.
	 * @return Pizza entity which has the referenced id
	 */
	@GetMapping("/{id}")
	@CrossOrigin(origins="http://localhost:8081")
	public Optional<Pizza> getPizza(@PathVariable String id) {
		return pizzaService.getPizzaById(id);
	}
	/**
	 * this method is used for getting the pizza entities which ahs lower price than maxPrice
	 * @param maxPrice - value to filter accordingly by user's request
	 * @return List of pizzas which has lower price than maxPrice.
	 */
	@GetMapping("/price/{maxPrice}")
	@CrossOrigin(origins="http://localhost:8081")
	public List<Pizza> getByPrice(@PathVariable("maxPrice") int maxPrice) {
		List<Pizza> pizzas = this.pizzaService.getPizzaByPrice(maxPrice);
		return pizzas;
	}

	/**
	 * This method used for getting the Pizzas which has the ingredient in their
	 * attributes.
	 * 
	 * @param ingredient - string value to filter accordingly by user's request
	 * @return List of pizzas which has ingredient in their attributes.
	 */
	@GetMapping("all/ingredients/{ingredient}")
	@CrossOrigin(origins="http://localhost:8081")
	public List<Pizza> getByIngredient(@PathVariable("ingredient") String ingredient) {
		List<Pizza> pizzas = this.pizzaService.getPizzaByIngredient(ingredient);
		return pizzas;
	}

	/**
	 * this method used for saving the entity in to the database.
	 * 
	 * @param pizza - entity to be saved
	 */
	@PutMapping
	@CrossOrigin(origins="http://localhost:8081")
	public void insert(@RequestBody Pizza pizza) {
		this.pizzaService.insertPizza(pizza);
	}
	
	/**
	 * this method used for saving multiple entites at the same time.
	 * 
	 * @param pizzas - entities to be saved.
	 */
	@PutMapping("/saveAll")
	@CrossOrigin(origins="http://localhost:8081")
	public void insertAll(@RequestBody List<Pizza> pizzas) {
		for (Pizza pizza : pizzas) {
			this.pizzaService.insertPizza(pizza);
		}
	}

	/**
	 * this method used for updating the referanced pizza in the database.
	 * 
	 * @param pizza - entity to be updated.
	 */
	@PostMapping
	@CrossOrigin(origins="http://localhost:8081")
	public void update(@RequestBody Pizza pizza) {
		this.pizzaService.updatePizza(pizza);
	}
	
	/**
	 * this method used for deleting the entity.
	 * 
	 * @param id - id of the entity to be deleted.
	 */
	@DeleteMapping(value = "/{id}")
	@CrossOrigin(origins="http://localhost:8081")
	public void deletePizza(@PathVariable("id") String id) {
		pizzaService.deletePizza(id);
	}
}

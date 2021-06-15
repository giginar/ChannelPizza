package com.kucukcinar.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kucukcinar.models.Pizza;
import com.kucukcinar.repository.PizzaRepository;

/**
 * Service class that contains methods about Pizza entity.
 * 
 * @author yigit
 * @version 0.1
 * 
 */
@Service
public class PizzaService {

	@Autowired
	private PizzaRepository pizzaRepository;

	/**
	 * This method is for getting all the pizzas saved in the database.
	 * 
	 * @return List of pizzas.
	 */
	public List<Pizza> getAllPizzas() {
		List<Pizza> pizzas = pizzaRepository.findAll();
		return pizzas;
	}

	/**
	 * This method used for getting one Pizza entity with the passed value of id.
	 * 
	 * @param id - Id of the referanced pizza in the database
	 * @return Pizza entity with the referred id.
	 */
	public Optional<Pizza> getPizzaById(String id) {
		return pizzaRepository.findById(id);
	}

	/**
	 * This method used for getting the pizzas which has less price than passed
	 * maxPrice value.
	 * 
	 * @param maxPrice - value to filter accordingly by user's request
	 * @return List of pizzas which has lower price than maxPrice.
	 */
	public List<Pizza> getPizzaByPrice(int maxPrice) {
		List<Pizza> pizzas = this.pizzaRepository.findByPriceLessThan(maxPrice);
		return pizzas;
	}

	/**
	 * This method used for getting the Pizzas which has the ingredient in their
	 * attributes.
	 * 
	 * @param ingredient - value to filter accordingly by user's request
	 * @return List of pizzas which has ingredient in their attributes.
	 */
	public List<Pizza> getPizzaByIngredient(String ingredient) {

		List<Pizza> pizzas = this.pizzaRepository.findAll();
		List<Pizza> ingPizza = new ArrayList<>();
		for (Pizza pizza : pizzas) {
			if (pizza.toString().toLowerCase().contains(ingredient.toLowerCase())) {
				ingPizza.add(pizza);
			}
		}
		return ingPizza;
	}

	/**
	 * this method used for saving the entity in to the database.
	 * 
	 * @param pizza - entity to be saved
	 */
	public void insertPizza(Pizza pizza) {
		this.pizzaRepository.insert(pizza);
	}

	/**
	 * this method used for saving multiple entites at the same time.
	 * 
	 * @param pizzas - entities to be saved.
	 */
	public void insertAllPizzas(List<Pizza> pizzas) {
		for (Pizza pizza : pizzas) {
			this.pizzaRepository.insert(pizza);
		}
	}

	/**
	 * this method used for updating the referanced pizza in the database.
	 * 
	 * @param pizza - entity to be updated.
	 */
	public void updatePizza(Pizza pizza) {
		this.pizzaRepository.save(pizza);
	}

	/**
	 * this method used for deleting the entity.
	 * 
	 * @param id - id of the entity to be deleted.
	 */
	public void deletePizza(String id) {
		pizzaRepository.deleteById(id);
	}

}

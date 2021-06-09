package com.kucukcinar.pizza;

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

@RestController
@RequestMapping("/pizzas")
@CrossOrigin
public class PizzaController {

	@Autowired // dependency injection.
	private PizzaService pizzaService;


	@GetMapping("/all")
	public List<Pizza> getAllPizzas() {
		return pizzaService.getAllPizzas();
	}

	@GetMapping("/{id}")
	public Optional<Pizza> getPizza(@PathVariable String id) {
		return pizzaService.getPizzaById(id);
	}

	@GetMapping("/price/{maxPrice}")
	public List<Pizza> getByPrice(@PathVariable("maxPrice") int maxPrice) {
		List<Pizza> pizzas = this.pizzaService.getPizzaByPrice(maxPrice);
		return pizzas;
	}

	@GetMapping("all/ingredients/{ingredient}")
	public List<Pizza> getByIngredient(@PathVariable("ingredient") String ingredient) {
		List<Pizza> pizzas = this.pizzaService.getPizzaByIngredient(ingredient);
		return pizzas;
	}

	@PutMapping
	public void insert(@RequestBody Pizza pizza) {
		this.pizzaService.insertPizza(pizza);
	}

	@PutMapping("/saveAll")
	public void insertAll(@RequestBody List<Pizza> pizzas) {
		for (Pizza pizza : pizzas) {
			this.pizzaService.insertPizza(pizza);
		}
	}

	@PostMapping
	public void update(@RequestBody Pizza pizza) {
		this.pizzaService.updatePizza(pizza);
	}

	@DeleteMapping(value = "/{id}")
	public void deletePizza(@PathVariable("id") String id) {
		pizzaService.deletePizza(id);
	}
}

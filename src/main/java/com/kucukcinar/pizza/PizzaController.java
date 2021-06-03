package com.kucukcinar.pizza;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pizzas")
public class PizzaController {

	// @Autowired // dependency injection.
	private PizzaRepository pizzaRepository;

	public PizzaController(PizzaRepository pizzaRepository) {
		this.pizzaRepository = pizzaRepository;
	}

	@GetMapping("/all")
	public List<Pizza> getAllPizzas() {
		return pizzaRepository.findAll();
	}

	@GetMapping("/{id}")
	public Optional<Pizza> getPizza(@PathVariable String id) {
		return pizzaRepository.findById(id);
	}
	
	@GetMapping("/price/{maxPrice}")
	public List<Pizza> getByPrice(@PathVariable("maxPrice") int maxPrice){
		List<Pizza> pizzas = this.pizzaRepository.findByPriceLessThan(maxPrice);
		return pizzas;
	}
	@GetMapping("/ingredients/{ingredient}")
	public List<Pizza> getByIngredient(@PathVariable("ingredient") String ingredient){
		List<Pizza> pizzas = this.pizzaRepository.findByIngredients(ingredient);
		return pizzas;
	}

	@PutMapping
	public void insert(@RequestBody Pizza pizza) {
		this.pizzaRepository.insert(pizza);
	}

	@PostMapping
	public void update(@RequestBody Pizza pizza) {
		this.pizzaRepository.save(pizza);
	}

	@DeleteMapping(value = "/{id}")
	public void deletePizza(@PathVariable("id") String id) {
		pizzaRepository.deleteById(id);
	}
}

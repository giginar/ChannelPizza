package com.kucukcinar.pizza;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Pizzas")
public class Pizza {
	
	@Id
	private String id;
	private String name;
	@Indexed(direction = IndexDirection.ASCENDING)
	private int price;
	private List<String> ingredients;

	public Pizza() {
		this.ingredients = new ArrayList<>();
	}

	public Pizza(String name, int price, List<String> ingredients) {
		super();
		this.name = name;
		this.price = price;
		this.ingredients = ingredients;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public List<String> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<String> ingredients) {
		this.ingredients = ingredients;
	}

	@Override
	public String toString() {
		return "Pizza [id=" + id + ", name=" + name + ", price=" + price + ", ingredients=" + ingredients + "]";
	}

	

}

package com.kucukcinar.services;

import com.kucukcinar.models.AppUser;
import com.kucukcinar.models.AppUserRole;
import com.kucukcinar.models.Pizza;
import com.kucukcinar.repository.AppUserRepository;
import com.kucukcinar.repository.PizzaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class PizzaServiceTest {

    @Autowired
    private PizzaService pizzaService;

    @MockBean
    private PizzaRepository pizzaRepository;

    @Test
    void getAllPizzasTest() {
        List<String> mockIng1 = new ArrayList<>(Arrays.asList("Mozzarella", "Salami", "Mushrooms"));
        List<String> mockIng2 = new ArrayList<>(Arrays.asList("Mozzarella", "Salami"));
        Pizza mockPizza1 = new Pizza(
                "Farm House", 40, mockIng1, "https://www.pizzahut.com.tr/CMSFiles/Product/LittleImage/ciftlik-evi.jpg"
        );
        Pizza mockPizza2 = new Pizza(
                "King House", 50, mockIng2, "https://www.pizzahut.com.tr/CMSFiles/Product/LittleImage/ciftlik-evi.jpg"
        );
        Mockito.when(pizzaRepository.findAll()).thenReturn(Stream
                .of(mockPizza1, mockPizza2).collect(Collectors.toList()));
        assertEquals(2, pizzaService.getAllPizzas().size());
    }

    @Test
    public void getPizzaByIngredientTest() {
        List<String> mockIng1 = new ArrayList<>(Arrays.asList("Mozzarella", "Salami", "Mushrooms"));
        Pizza mockPizza1 = new Pizza(
                "Farm House", 40, mockIng1, "https://www.pizzahut.com.tr/CMSFiles/Product/LittleImage/ciftlik-evi.jpg"
        );

        ArrayList<Pizza> pizzaList = new ArrayList<Pizza>();
        pizzaList.add(mockPizza1);
        when(this.pizzaRepository.findAll()).thenReturn(pizzaList);
        assertEquals(1, this.pizzaService.getPizzaByIngredient("Ingredient").size());
        verify(this.pizzaRepository).findAll();
        assertEquals(1, this.pizzaService.getAllPizzas().size());
    }

    @Test
    public void getPizzaByIngredientWhenEmptyTest() {
        when(this.pizzaRepository.findAll()).thenReturn(new ArrayList<Pizza>());
        assertTrue(this.pizzaService.getPizzaByIngredient("Ingredient").isEmpty());
        verify(this.pizzaRepository).findAll();
        assertTrue(this.pizzaService.getAllPizzas().isEmpty());
    }

    @Test
    void insertPizzaTest() {
        List<String> mockIng1 = new ArrayList<>(Arrays.asList("Mozzarella", "Salami", "Mushrooms"));
        Pizza mockPizza1 = new Pizza(
                "Farm House", 40, mockIng1, "https://www.pizzahut.com.tr/CMSFiles/Product/LittleImage/ciftlik-evi.jpg"
        );
        when(this.pizzaRepository.insert((Pizza) any())).thenReturn(mockPizza1);
        this.pizzaService.insertPizza(new Pizza());
        verify(this.pizzaRepository).insert((Pizza) any());
        assertTrue(this.pizzaService.getAllPizzas().isEmpty());
    }

    @Test
    public void insertAllPizzasTest() {
        List<String> mockIng1 = new ArrayList<>(Arrays.asList("Mozzarella", "Salami", "Mushrooms"));
        List<String> mockIng2 = new ArrayList<>(Arrays.asList("Mozzarella", "Salami"));
        Pizza mockPizza1 = new Pizza(
                "Farm House", 40, mockIng1, "https://www.pizzahut.com.tr/CMSFiles/Product/LittleImage/ciftlik-evi.jpg"
        );
        Pizza mockPizza2 = new Pizza(
                "King House", 50, mockIng2, "https://www.pizzahut.com.tr/CMSFiles/Product/LittleImage/ciftlik-evi.jpg"
        );

        ArrayList<Pizza> pizzaList = new ArrayList<Pizza>();
        pizzaList.add(mockPizza1);
        pizzaList.add(mockPizza2);
        this.pizzaService.insertAllPizzas(pizzaList);
        Mockito.when(pizzaRepository.findAll()).thenReturn(Stream
                .of(mockPizza1, mockPizza2).collect(Collectors.toList()));
        assertEquals(2, pizzaService.getAllPizzas().size());
    }

    @Test
    public void insertAllPizzasWhenEmptyTest() {
        this.pizzaService.insertAllPizzas(new ArrayList<Pizza>());
        assertTrue(this.pizzaService.getAllPizzas().isEmpty());
    }

    @Test
    public void updatePizzaTest() {
        List<String> mockIng1 = new ArrayList<>(Arrays.asList("Mozzarella", "Salami", "Mushrooms"));
        Pizza mockPizza1 = new Pizza(
                "Farm House", 40, mockIng1, "https://www.pizzahut.com.tr/CMSFiles/Product/LittleImage/ciftlik-evi.jpg"
        );
        when(this.pizzaRepository.save((Pizza) any())).thenReturn(mockPizza1);
        Mockito.when(pizzaRepository.findAll()).thenReturn(Stream
                .of(mockPizza1).collect(Collectors.toList()));
        assertEquals(1, pizzaService.getAllPizzas().size());
    }

    @Test
    void deletePizzaTest() {
        doNothing().when(this.pizzaRepository).deleteById(anyString());
        this.pizzaService.deletePizza("1");
        verify(this.pizzaRepository).deleteById(anyString());
        assertTrue(this.pizzaService.getAllPizzas().isEmpty());
    }
}
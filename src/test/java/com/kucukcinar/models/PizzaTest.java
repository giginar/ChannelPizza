package com.kucukcinar.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

public class PizzaTest {

    /**
     * Constructor test for Pizza object
     */
    @Test
    public void constructorTest() {

        List<String> mockIng1 = new ArrayList<>(Arrays.asList("Mozzarella", "Salami", "Mushrooms"));
        Pizza mockPizza1 = new Pizza(
                "Farm House", 40, mockIng1, "https://www.pizzahut.com.tr/CMSFiles/Product/LittleImage/ciftlik-evi.jpg"
        );

        Pizza actualPizza = new Pizza("Farm House", 40, new ArrayList<String>(), "Image");
        mockPizza1.setImage("Image");
        ArrayList<String> stringList = new ArrayList<String>();
        mockPizza1.setIngredients(mockIng1);
        mockPizza1.setName("Farm House");
        mockPizza1.setPrice(40);
        assertNull(mockPizza1.getId());
        assertEquals("Image", mockPizza1.getImage());
        assertSame(mockIng1, mockPizza1.getIngredients());
        assertEquals("Farm House", mockPizza1.getName());
        assertEquals(40, mockPizza1.getPrice());
        assertEquals("name=Farm House, price=40, ingredients=[Mozzarella, Salami, Mushrooms]" , mockPizza1.toString());
    }
}


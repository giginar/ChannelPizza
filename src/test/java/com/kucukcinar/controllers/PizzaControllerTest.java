package com.kucukcinar.controllers;

import com.kucukcinar.models.Pizza;
import com.kucukcinar.services.AppUserService;
import com.kucukcinar.services.PizzaService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.stubbing.OngoingStubbing;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
@WebMvcTest(PizzaController.class)
@WithMockUser
class PizzaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PizzaService pizzaService;

    @MockBean
    private AppUserService appUserService;

    List<String> mockIng = new ArrayList<>(Arrays.asList("Mozarella","Salami","Mushrooms"));
    Pizza mockPizza = new Pizza(
            "Farm House",40,mockIng,"https://www.pizzahut.com.tr/CMSFiles/Product/LittleImage/ciftlik-evi.jpg"
    );
    List<Pizza> mockList = new ArrayList<>(Arrays.asList(mockPizza));
    String pizzaJson = " {\n" +
            "    \"name\": \"Farm House\",\n" +
            "    \"price\": 40,\n" +
            "    \"ingredients\": [\n" +
            "      \"Mozarella\",\n" +
            "      \"Salami\",\n" +
            "      \"Mushrooms\"\n" +
            "    ],\n" +
            "    \"image\": \"https://www.pizzahut.com.tr/CMSFiles/Product/LittleImage/ciftlik-evi.jpg\"\n" +
            "  }";

    @Test
    void getAllPizzas() throws Exception {

//        Mockito.when(pizzaService.getAllPizzas()).thenReturn(mockList);
//        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
//                "/pizzas/getAllPizzas"
//        ).accept(MediaType.APPLICATION_JSON);
//
//        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
//        System.out.println(result.getResponse());
//        String expected = pizzaJson;
//        JSONAssert.assertEquals(expected,result.getResponse().getContentAsString(),false);
    }

    @Test
    @Disabled
    void getByIngredient() {
    }

    @Test
    void insert() throws Exception {
//        Mockito.doThrow(new Exception()).doNothing().when(pizzaService.insertPizza(Mockito.any(Pizza.class)));
//        RequestBuilder requestBuilder = MockMvcRequestBuilders
//                .post("/pizzas")
//                .accept(MediaType.APPLICATION_JSON).content(pizzaJson)
//                .contentType(MediaType.APPLICATION_JSON);
//        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
//        MockHttpServletResponse response =result.getResponse();
//        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
//        assertEquals("http://localhost/pizzas",response.getHeader(HttpHeaders.LOCATION));
    }

    @Test
    @Disabled
    void insertAll() {
    }

    @Test
    @Disabled
    void update() {
    }

    @Test
    @Disabled
    void deletePizza() {
    }
}
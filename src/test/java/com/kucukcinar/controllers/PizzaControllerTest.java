package com.kucukcinar.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;


/**
 * Testing Controller for pizza entity
 */
@ContextConfiguration(classes = {PizzaController.class})
@ExtendWith(SpringExtension.class)
@WebMvcTest(PizzaController.class)
@WithMockUser
class PizzaControllerTest {

    @Autowired
    private PizzaController pizzaController;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PizzaService pizzaService;

    @MockBean
    private AppUserService appUserService;

    List<String> mockIng = new ArrayList<>(Arrays.asList("Mozarella", "Salami", "Mushrooms"));
    Pizza mockPizza = new Pizza(
            "Farm House", 40, mockIng, "https://www.pizzahut.com.tr/CMSFiles/Product/LittleImage/ciftlik-evi.jpg"
    );
    List<Pizza> mockList = new ArrayList<>(Arrays.asList(mockPizza));
    String pizzaJson = "{" + "\"id\":null," + "\"name\":\"Farm House\"," + "\"price\":40," + "\"ingredients\":[" +
            "\"Mozarella\"," + "\"Salami\"," + "\"Mushrooms\"" + "]," + "\"image\":\"https://www.pizzahut.com.tr/CMSFiles/Product/LittleImage/ciftlik-evi.jpg\"" + "}";

    /**
     * This method used for getting all pizzas
     * and tested with mocked pizza expecting return of the onject in json format.
     * @throws Exception
     */
    @Test
    public void getAllPizzasTest() throws Exception {
        List<Pizza> mockList = new ArrayList<>(Arrays.asList(mockPizza));
        when(this.pizzaService.getAllPizzas()).thenReturn(mockList);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/pizzas/getAllPizzas");
        MockMvcBuilders.standaloneSetup(this.pizzaController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[" + pizzaJson + "]"));
    }

    /**
     * This method used for testing "the search with ingredient pizza object"
     * @throws Exception
     */
    @Test
    public void getByIngredientTest() throws Exception {
        when(this.pizzaService.getPizzaByIngredient("Salami")).thenReturn(mockList);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/pizzas/all/ingredients/{ingredient}",
                "Salami");
        MockMvcBuilders.standaloneSetup(this.pizzaController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[" + pizzaJson + "]"));
    }

    /**
     * Testing inserting objects
     * @throws Exception
     */
    @Test
    public void insertAllTest() throws Exception {
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders.put("/pizzas/saveAll")
                .contentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content(objectMapper.writeValueAsString(mockList));
        MockMvcBuilders.standaloneSetup(this.pizzaController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Testing updating object
     * @throws Exception
     */
    @Test
    public void UpdateTest() throws Exception {
        doNothing().when(this.pizzaService).updatePizza(mockPizza);
        String content = (new ObjectMapper()).writeValueAsString(mockPizza);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/pizzas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.pizzaController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(405));
    }

    /**
     * Testing deleting object
     * @throws Exception
     */
    @Test
    public void deletePizzaTest() throws Exception {
        doNothing().when(this.pizzaService).deletePizza(anyString());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/pizzas/{id}", "42");
        MockMvcBuilders.standaloneSetup(this.pizzaController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }


}
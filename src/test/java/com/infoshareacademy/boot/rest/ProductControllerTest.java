package com.infoshareacademy.boot.rest;

import com.fasterxml.jackson.databind.json.JsonMapper;
import com.infoshareacademy.boot.BootApplication;
import com.infoshareacademy.boot.dto.ProductDto;
import com.infoshareacademy.boot.mapper.ProductMapper;
import com.infoshareacademy.boot.model.Product;
import com.infoshareacademy.boot.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = BootApplication.class)
@AutoConfigureMockMvc
@EnableAutoConfiguration
@ActiveProfiles(profiles = "test")
public class ProductControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ProductRepository repository;

    @Autowired
    private ProductMapper mapper;

    @Test
    public void shouldReturnProduct() throws Exception {
        // given
        Product expected = repository.save(new Product(null, "water", 2.00));

        // when
        mvc.perform(get("/api/product/" + expected.getId())
                .contentType(MediaType.APPLICATION_JSON))
                // then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(expected.getId().intValue())))
                .andExpect(jsonPath("$.name", is(expected.getName())))
                .andExpect(jsonPath("$.price", is(expected.getPrice())));
    }

    @Test
    public void shouldUpdateProduct() throws Exception {
        // given
        Product saved = repository.save(new Product(null, "water", 2.00));
        ProductDto expected = mapper.toDto(saved);
        expected.setPrice(1000.0);

        // when
        mvc.perform(put("/api/product/")
                .content(new JsonMapper().writeValueAsString(expected))
                .contentType(MediaType.APPLICATION_JSON))
                // then
                .andExpect(status().isOk());

        mvc.perform(get("/api/product/" + expected.getId())
                .contentType(MediaType.APPLICATION_JSON))
                // then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(expected.getId().intValue())))
                .andExpect(jsonPath("$.name", is(expected.getName())))
                .andExpect(jsonPath("$.price", is(expected.getPrice())));
    }

    @Test
    public void shouldReturnConflictWhenProductWithNegativePrice() throws Exception {
        // given
        // when
        mvc.perform(put("/api/product/")
                .content(new JsonMapper().writeValueAsString(new Product(null, "water", -2.00)))
                .contentType(MediaType.APPLICATION_JSON))
                // then
                .andExpect(status().isConflict());
    }

    @Test
    public void shouldReturnBadRequestWhenProductHasNoName() throws Exception {
        // given
        // when
        mvc.perform(put("/api/product/")
                .content(new JsonMapper().writeValueAsString(new Product(null, null, 2.00)))
                .contentType(MediaType.APPLICATION_JSON))
                // then
                .andExpect(status().isBadRequest());
    }
}

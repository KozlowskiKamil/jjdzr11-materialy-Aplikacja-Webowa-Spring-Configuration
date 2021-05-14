package com.infoshareacademy.boot.rest;

import com.infoshareacademy.boot.BootApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = BootApplication.class)
@AutoConfigureMockMvc
@EnableAutoConfiguration
public class DataReaderTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void shouldReturnPredefinedProducts() throws Exception {
        // given
        // when
        mvc.perform(get("/products")
                .contentType(MediaType.APPLICATION_JSON))
                // then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("phone")))
                .andExpect(jsonPath("$[0].price", is(22.22)))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].name", is("tv")))
                .andExpect(jsonPath("$[1].price", is(33.22)));
    }

    @Test
    public void shouldReturnPredefinedBooks() throws Exception {
        // given
        // when
        mvc.perform(get("/books")
                .contentType(MediaType.APPLICATION_JSON))
                // then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].title", is("good book")))
                .andExpect(jsonPath("$[0].pages", is(300)))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].title", is("a story")))
                .andExpect(jsonPath("$[1].pages", is(1300)));
    }
}

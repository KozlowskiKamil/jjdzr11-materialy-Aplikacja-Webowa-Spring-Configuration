package com.infoshareacademy.boot.rest;

import com.fasterxml.jackson.databind.json.JsonMapper;
import com.infoshareacademy.boot.BootApplication;
import com.infoshareacademy.boot.dto.BookDto;
import com.infoshareacademy.boot.mapper.BookMapper;
import com.infoshareacademy.boot.model.Book;
import com.infoshareacademy.boot.repository.BookRepository;
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
public class BookControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private BookRepository repository;

    @Autowired
    private BookMapper mapper;

    @Test
    public void shouldReturnProduct() throws Exception {
        // given
        Book expected = repository.save(new Book(null, "Dzieci z Bullerbyn ", 2000));

        // when
        mvc.perform(get("/api/book/" + expected.getId())
                .contentType(MediaType.APPLICATION_JSON))
                // then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(expected.getId().intValue())))
                .andExpect(jsonPath("$.title", is(expected.getTitle())))
                .andExpect(jsonPath("$.pages", is(expected.getPages())));
    }

    @Test
    public void shouldUpdateProduct() throws Exception {
        // given
        Book saved = repository.save(new Book(null, "water", 2000));
        BookDto expected = mapper.toDto(saved);
        expected.setPages(200);

        // when
        mvc.perform(put("/api/book/")
                .content(new JsonMapper().writeValueAsString(expected))
                .contentType(MediaType.APPLICATION_JSON))
                // then
                .andExpect(status().isOk());

        mvc.perform(get("/api/book/" + expected.getId())
                .contentType(MediaType.APPLICATION_JSON))
                // then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(expected.getId().intValue())))
                .andExpect(jsonPath("$.title", is(expected.getTitle())))
                .andExpect(jsonPath("$.pages", is(expected.getPages())));
    }
}

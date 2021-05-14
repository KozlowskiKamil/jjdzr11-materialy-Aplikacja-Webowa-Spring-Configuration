package com.infoshareacademy.boot.rest;

import com.infoshareacademy.boot.BootApplication;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = BootApplication.class)
@AutoConfigureMockMvc
@EnableAutoConfiguration
@ActiveProfiles(profiles = "test")
public class ThreadPropsControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void shouldReturnBatch() throws Exception {
        // given
        // when
        mvc.perform(get("/api/thread/batch")
                .contentType(MediaType.APPLICATION_JSON))
                // then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is(50)));
    }

    @Test
    public void shouldReturnThreads() throws Exception {
        // given
        // when
        mvc.perform(get("/api/thread/threads")
                .contentType(MediaType.APPLICATION_JSON))
                // then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is(4)));
    }

    @Test
    public void shouldReturnProps() throws Exception {
        // given
        // when
        mvc.perform(get("/api/thread/all")
                .contentType(MediaType.APPLICATION_JSON))
                // then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.batch", is(50)))
                .andExpect(jsonPath("$.threads", is(4)))
                .andExpect(jsonPath("$.name", is("importantThread")));
    }
}

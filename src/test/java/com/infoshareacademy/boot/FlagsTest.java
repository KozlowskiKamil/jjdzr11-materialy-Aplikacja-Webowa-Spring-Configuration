package com.infoshareacademy.boot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = BootApplication.class)
@AutoConfigureMockMvc
@EnableAutoConfiguration
@ActiveProfiles(profiles = "test")
@DirtiesContext(classMode = BEFORE_EACH_TEST_METHOD)
public class FlagsTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void shouldReturnInitFlags() throws Exception {
        // given
        // when
        mvc.perform(get("/actuator/flags/")
                .contentType(MediaType.APPLICATION_JSON))
                // then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.feature1", is(false)))
                .andExpect(jsonPath("$.feature2", is(false)));
    }

    @Test
    public void shouldUpdateFlag() throws Exception {
        // given
        // when
        mvc.perform(post("/actuator/flags/feature1")
                .content("{\"enabled\":true}")
                .contentType(MediaType.APPLICATION_JSON))
                // then
                .andExpect(status().isNoContent());

        mvc.perform(get("/actuator/flags/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.feature1", is(true)))
                .andExpect(jsonPath("$.feature2", is(false)));
    }

    @Test
    public void shouldAddFlag() throws Exception {
        // given
        // when
        mvc.perform(post("/actuator/flags/feature3")
                .content("{\"enabled\":true}")
                .contentType(MediaType.APPLICATION_JSON))
                // then
                .andExpect(status().isNoContent());

        mvc.perform(get("/actuator/flags/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.feature1", is(false)))
                .andExpect(jsonPath("$.feature2", is(false)))
                .andExpect(jsonPath("$.feature3", is(true)));
    }

    @Test
    public void shouldRemoveFlag() throws Exception {
        // given
        // when
        mvc.perform(delete("/actuator/flags/feature2")
                .contentType(MediaType.APPLICATION_JSON))
                // then
                .andExpect(status().isNoContent());

        mvc.perform(get("/actuator/flags/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.feature1", is(false)));
    }
}

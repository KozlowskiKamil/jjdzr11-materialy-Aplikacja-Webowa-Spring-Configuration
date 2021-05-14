package com.infoshareacademy.boot.aspect;

import com.infoshareacademy.boot.BootApplication;
import com.infoshareacademy.boot.dto.BookDto;
import com.infoshareacademy.boot.mapper.BookMapper;
import com.infoshareacademy.boot.mapper.ProductMapper;
import com.infoshareacademy.boot.model.Book;
import com.infoshareacademy.boot.model.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = BootApplication.class)
@AutoConfigureMockMvc
@EnableAutoConfiguration
@ActiveProfiles(profiles = "test")
// This is not a real test class. It will help to rerun some method to show AOP behaviour
public class AdviceTest {

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private BookMapper bookMapper;

    @Test
    public void runProductMapper() {
        productMapper.toDto(new Product(1L, "water", 2.00));
    }

    @Test
    public void runProductMapperWithException() {
        assertThrows(NullPointerException.class, () -> {
            productMapper.toDto(null);
        });
    }

    @Test
    public void runBookMapper() {
        bookMapper.toDto(new Book(1L, "A book", 200));
    }

    @Test
    public void runBookMapperWithException() {
        assertThrows(NullPointerException.class, () -> {
            bookMapper.toDto(null);
        });
    }

    @Test
    public void runBookMapperFromDto() {
        bookMapper.fromDto(new BookDto(1L, "A book", 200));
    }
}

package com.infoshareacademy.boot.rest;

import com.infoshareacademy.boot.dto.ProductDto;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;

@RestController
@ConfigurationProperties(prefix = "data")
public class DataReader {

    private List<ProductDto> products = new LinkedList<>();

    public void setProducts(List<ProductDto> products) {
        this.products = products;
    }

    public List<ProductDto> getProducts() {
        return products;
    }

    @GetMapping("/products")
    public List<ProductDto> show() {
        return products;
    }
}

package com.infoshareacademy.boot.rest;

import com.infoshareacademy.boot.dto.ProductDto;
import com.infoshareacademy.boot.mapper.ProductMapper;
import com.infoshareacademy.boot.model.Product;
import com.infoshareacademy.boot.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    private static Logger logger = LoggerFactory.getLogger(ProductController.class);

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductController(@Autowired ProductRepository productRepository,
                             @Autowired ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable Long id) {
        Optional<Product> opt = productRepository.findById(id);
        if (opt.isEmpty()) {
            logger.info("Not Found");
            return ResponseEntity.notFound().build();
        }
        ProductDto dto = productMapper.toDto(opt.get());
        logger.info(dto.toString());

        return ResponseEntity.ok(dto);
    }

    @PutMapping
    public ResponseEntity<ProductDto> upsert(@RequestBody ProductDto dto) {
        if (dto.getName() == null) {
            throw new IllegalArgumentException("ProductDto.Name cannot be null");
        }

        if (dto.getPrice() < 0) {
            throw new IllegalStateException("ProductDto.Price cannot be lower than 0!");
        }

        Product product = productRepository.save(productMapper.fromDto(dto));
        return ResponseEntity.ok(productMapper.toDto(product));
    }
}

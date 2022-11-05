package com.infoshareacademy.boot.rest;

import com.infoshareacademy.boot.dto.BookDto;
import com.infoshareacademy.boot.dto.ProductDto;
import com.infoshareacademy.boot.mapper.BookMapper;
import com.infoshareacademy.boot.mapper.ProductMapper;
import com.infoshareacademy.boot.model.Book;
import com.infoshareacademy.boot.model.Product;
import com.infoshareacademy.boot.repository.BookRepository;
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
@RequestMapping("/api/book")
public class BookController {
    private static Logger logger = LoggerFactory.getLogger(BookController.class);

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public BookController(@Autowired BookRepository bookRepository,
                          @Autowired BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDto> getProduct(@PathVariable Long id) {
        Optional<Book> opt = bookRepository.findById(id);
        if (opt.isEmpty()) {
            logger.info("Not Found");
            return ResponseEntity.notFound().build();
        }
        BookDto dto = bookMapper.toDto(opt.get());
        logger.info(dto.toString());

        return ResponseEntity.ok(dto);
    }

    @PutMapping
    public ResponseEntity<BookDto> upsert(@RequestBody BookDto dto) {
        Book book = bookRepository.save(bookMapper.fromDto(dto));
        return ResponseEntity.ok(bookMapper.toDto(book));
    }
}

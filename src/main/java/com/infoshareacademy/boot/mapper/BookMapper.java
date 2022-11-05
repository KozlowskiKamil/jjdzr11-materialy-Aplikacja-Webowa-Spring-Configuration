package com.infoshareacademy.boot.mapper;

import com.infoshareacademy.boot.aspects.MeasureTime;
import com.infoshareacademy.boot.aspects.Retry;
import com.infoshareacademy.boot.dto.BookDto;
import com.infoshareacademy.boot.model.Book;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {

    @MeasureTime
    public BookDto toDto(Book book) {
        BookDto dto = new BookDto();
        dto.setId(book.getId());
        dto.setTitle(book.getTitle());
        dto.setPages(book.getPages());
        return dto;
    }

    @Retry
    public Book fromDto(BookDto dto) {
        Book book = new Book();
        book.setId(dto.getId());
        book.setTitle(dto.getTitle());
        book.setPages(dto.getPages());
        return book;
    }
}

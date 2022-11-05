package com.infoshareacademy.boot.dto;

import java.util.Objects;

public class BookDto {

    private Long id;
    private String title;
    private Integer pages;

    public BookDto() {
    }

    public BookDto(Long id, String title, Integer pages) {
        this.id = id;
        this.title = title;
        this.pages = pages;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookDto bookDto = (BookDto) o;
        return Objects.equals(id, bookDto.id) && Objects.equals(title, bookDto.title) && Objects.equals(pages, bookDto.pages);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, pages);
    }

    @Override
    public String toString() {
        return "BookDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", pages=" + pages +
                '}';
    }
}

package com.example.seebook.domain.book.domain;

import com.example.seebook.domain.book.dto.BookDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;

    private String isbn13;
    private String title;
    private String author;
    private String publisher;
    private String description;
    private String buyLink;
    private String imageLink;
    private String categoryId;
    private String categoryName;

    @Builder
    public Book(Long bookId, String isbn13, String title, String author, String publisher, String description, String buyLink, String imageLink, String categoryId, String categoryName) {
        this.bookId = bookId;
        this.isbn13 = isbn13;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.description = description;
        this.buyLink = buyLink;
        this.imageLink = imageLink;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    public static Book form(BookDTO bookDTO) {
        return Book.builder()
                .isbn13(bookDTO.getIsbn13())
                .title(bookDTO.getTitle())
                .author(bookDTO.getAuthor())
                .publisher(bookDTO.getPublisher())
                .description(bookDTO.getDescription())
                .buyLink(bookDTO.getBuyLink())
                .imageLink(bookDTO.getImageLink())
                .categoryId(bookDTO.getCategoryId())
                .categoryName(bookDTO.getCategoryName())
                .build();
    }

}

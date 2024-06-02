package com.example.seebook.domain.book.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
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
    private String wishlistCount;
    private String category;
    private String categoryId;
}

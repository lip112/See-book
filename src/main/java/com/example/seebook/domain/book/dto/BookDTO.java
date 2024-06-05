package com.example.seebook.domain.book.dto;

import com.example.seebook.domain.book.domain.Book;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BookDTO {
    private Long bookId;
    private String title;
    private String buyLink;
    private String author;
    private String description;
    private String isbn13;
    private String imageLink;
    private String categoryId;
    private String categoryName;
    private String publisher;

    @Builder
    public BookDTO(Long bookId, String title, String buyLink, String author, String description, String isbn13, String imageLink, String categoryId, String categoryName, String publisher) {
        this.bookId = bookId;
        this.title = title;
        this.buyLink = buyLink;
        this.author = author;
        this.description = description;
        this.isbn13 = isbn13;
        this.imageLink = imageLink;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.publisher = publisher;
    }
    public static BookDTO form(Book book) {
        return BookDTO.builder()
                .bookId(book.getBookId())
                .title(book.getTitle())
                .imageLink(book.getImageLink())
                .publisher(book.getPublisher())
                .description(book.getDescription())
                .categoryName(book.getCategoryName())
                .categoryId(book.getCategoryId())
                .buyLink(book.getBuyLink())
                .author(book.getAuthor())
                .isbn13(book.getIsbn13())
                .build();
    }


}

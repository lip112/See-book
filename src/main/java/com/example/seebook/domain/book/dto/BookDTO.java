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
    private Double avgStar = 0.0;
    private Long totalReviewCount = 0L;;
    private Long wishlistCount = 0L;;

    public void addWishlistCount(Long wishlistCount) {
        this.wishlistCount = wishlistCount;
    }

    public void addAvgStar(double avgStar) {
        this.avgStar = avgStar;
    }
    public void addTotalReviewCount(Long totalReviewCount) {
        this.totalReviewCount = totalReviewCount;
    }

    public void changeBookId(Long bookId) {
        this.bookId = bookId;
    }

    @Builder
    public BookDTO(Long bookId, String title, String buyLink, String author, String description, String isbn13, String imageLink, String categoryId, String categoryName, String publisher, Double avgStar, Long totalReviewCount) {
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
        this.avgStar = (avgStar != null) ? avgStar : 0.0;
        this.totalReviewCount = (totalReviewCount != null) ? totalReviewCount : 0L;
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

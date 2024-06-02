package com.example.seebook.domain.book.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class BookInfoResponseDTO {

    private String reviewCount;
    private int endPage;
    private Book book;
    private Review review;

    public static BookInfoResponseDTO form(String reviewCount, Book book, Review review) {
        return BookInfoResponseDTO.builder()
                .reviewCount(reviewCount)
                .endPage(Integer.parseInt(reviewCount) / 10 + 1)
                .build();
    }

    private static class Review {
        private Long userId;
        private String profileImage;
        private String nickname;
        private int level;
        private Long starRating;
        private String content;
    }
    @Getter
    private static class Book {
        private String title;
        private String link;
        private String author;
        private String pubDate;
        private String description;
        private String isbn13;
        private String imageLink;
        private String categoryId;
        private String categoryName;
        private String publisher;

        @Builder
        public Book(String title, String link, String author, String pubDate, String description, String isbn13, String imageLink, String categoryId, String categoryName, String publisher) {
            this.title = title;
            this.link = link;
            this.author = author;
            this.pubDate = pubDate;
            this.description = description;
            this.isbn13 = isbn13;
            this.imageLink = imageLink;
            this.categoryId = categoryId;
            this.categoryName = categoryName;
            this.publisher = publisher;
        }
    }
}

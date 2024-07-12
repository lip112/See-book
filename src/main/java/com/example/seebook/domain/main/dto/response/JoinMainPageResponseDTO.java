package com.example.seebook.domain.main.dto.response;

import com.example.seebook.domain.book.dto.BookDTO;
import com.example.seebook.domain.event.dto.EventDTO;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

public class JoinMainPageResponseDTO {
    private int eventCount;
    private int endPage;
    private List<EventDTO> event;
    private List<BookDTO> newBook;
    private List<BookWithReview> bestBook;

    @Builder
    public JoinMainPageResponseDTO(int eventCount, int endPage, List<EventDTO> event, List<BookDTO> newBook, List<BookWithReview> bestBook) {
        this.eventCount = eventCount;
        this.endPage = endPage;
        this.event = event;
        this.newBook = newBook;
        this.bestBook = bestBook;
    }

    @Getter
    public static class BookWithReview {
        private String title;
        private String author;
        private String isbn13;
        private String imageLink;
        private String publisher;
        private Double avgStar = 0.0;
        private Long totalReviewCount = 0L;
        private String nickname;
        private String content;

        @Builder
        public BookWithReview(String title, String author, String isbn13, String imageLink, String publisher, Double avgStar, Long totalReviewCount, String nickname, String content) {
            this.title = title;
            this.author = author;
            this.isbn13 = isbn13;
            this.imageLink = imageLink;
            this.publisher = publisher;
            this.avgStar = avgStar;
            this.totalReviewCount = totalReviewCount;
            this.nickname = nickname;
            this.content = content;
        }
    }
}

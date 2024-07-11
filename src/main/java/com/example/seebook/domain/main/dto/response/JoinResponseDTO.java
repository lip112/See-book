package com.example.seebook.domain.main.dto.response;

import com.example.seebook.domain.book.dto.BookDTO;
import com.example.seebook.domain.event.dto.EventDTO;
import com.example.seebook.domain.review.dto.ReviewDTO;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

public class JoinResponseDTO {
    private int eventCount;
    private int endPage;
    private List<EventDTO> event;
    private List<BookDTO> newBook;

    private List<BookWithReview> bookWithReview;


    @Builder
    public JoinResponseDTO(int eventCount, int endPage, List<EventDTO> event, List<BookDTO> newBook, List<BookWithReview> bookWithReview) {
        this.eventCount = eventCount;
        this.endPage = endPage;
        this.event = event;
        this.newBook = newBook;
        this.bookWithReview = bookWithReview;
    }

    @Getter
    static class BookWithReview{
        private List<BookDTO> book;
        private List<ReviewDTO> review;

        @Builder
        public BookWithReview(List<BookDTO> book, List<ReviewDTO> review) {
            this.book = book;
            this.review = review;
        }
    }

}

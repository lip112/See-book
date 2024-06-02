package com.example.seebook.domain.review.domain;

import com.example.seebook.domain.book.domain.Book;
import com.example.seebook.domain.user.domain.User;
import com.example.seebook.global.audit.BaseTime;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor
public class Review extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;

    @ManyToOne
    private User userId;

    @ManyToOne
    private Book bookId;

    private String content;

    private Long starRating;

    public void changeContent(String content) {
        this.content = content;
    }

    public void changeStarRating(Long starRating) {
        this.starRating = starRating;
    }

    @Builder
    public Review(User userId, Book bookId, String content, Long starRating) {
        this.userId = userId;
        this.bookId = bookId;
        this.content = content;
        this.starRating = starRating;
    }
}

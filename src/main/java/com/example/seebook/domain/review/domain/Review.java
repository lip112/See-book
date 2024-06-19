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
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    private String nickname;

    private String content;

    private Double starRating;

    public void changeContent(String content) {
        this.content = content;
    }

    public void changeStarRating(Double starRating) {
        this.starRating = starRating;
    }

    @Builder
    public Review(User user, Book book, String content, Double starRating) {
        this.user = user;
        this.book = book;
        this.content = content;
        this.starRating = starRating;
    }
}

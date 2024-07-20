package com.example.seebook.domain.review.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ProfileReviewDTO {
    private Long userId;
    private String isbn13;
    private String title;
    private String imageLink;
    private Long reviewId;
    private String content;
    private Double starRating;
    private LocalDateTime createdDate;

    @Builder
    public ProfileReviewDTO(Long userId, Long reviewId, String isbn13, String imageLink, String title, String content, Double starRating, LocalDateTime createdDate) {
        this.userId = userId;
        this.reviewId = reviewId;
        this.isbn13 = isbn13;
        this.imageLink = imageLink;
        this.title = title;
        this.content = content;
        this.starRating = starRating;
        this.createdDate = createdDate;
    }
}

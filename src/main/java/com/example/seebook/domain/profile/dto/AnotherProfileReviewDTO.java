package com.example.seebook.domain.profile.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class AnotherProfileReviewDTO {
    private String isbn13;
    private String imageLink;
    private String title;
    private Double avgBookStarRating;
    private String content;
    private Double reviewStarRating;
    private LocalDateTime createdDate;

    @Builder
    public AnotherProfileReviewDTO(String isbn13, String imageLink, String title, Double avgBookStarRating, String content, Double reviewStarRating, LocalDateTime createdDate) {
        this.isbn13 = isbn13;
        this.imageLink = imageLink;
        this.title = title;
        this.avgBookStarRating = avgBookStarRating;
        this.content = content;
        this.reviewStarRating = reviewStarRating;
        this.createdDate = createdDate;
    }
}

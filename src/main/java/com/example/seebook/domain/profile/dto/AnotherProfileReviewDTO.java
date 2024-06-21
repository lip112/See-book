package com.example.seebook.domain.profile.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class AnotherProfileReviewDTO {
    private String isbn13;
    private String imageLink;
    private String title;
    private String content;
    private Double starRating;

    @Builder
    public AnotherProfileReviewDTO(String isbn13, String imageLink, String title, String content, Double starRating) {
        this.isbn13 = isbn13;
        this.imageLink = imageLink;
        this.title = title;
        this.content = content;
        this.starRating = starRating;
    }
}

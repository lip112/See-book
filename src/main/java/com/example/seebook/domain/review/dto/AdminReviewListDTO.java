package com.example.seebook.domain.review.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class AdminReviewListDTO {
    private Long reviewId;
    private String nickname;
    private LocalDateTime createDate;
    private Double starRating;
    private String content;
    private String title;
    private String author;


    @Builder
    public AdminReviewListDTO(Long reviewId, String nickname, LocalDateTime createDate, Double starRating, String content, String title, String author) {
        this.reviewId = reviewId;
        this.nickname = nickname;
        this.createDate = createDate;
        this.starRating = starRating;
        this.content = content;
        this.title = title;
        this.author = author;
    }
}

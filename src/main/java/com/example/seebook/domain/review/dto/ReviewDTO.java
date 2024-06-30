package com.example.seebook.domain.review.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ReviewDTO {
    private Long reviewId;
    private Long userId;
    private String profileImage;
    private String nickname;
    private int level;
    private Double starRating = 0.0;
    private String content;
    @Builder
    public ReviewDTO(Long reviewId, Long userId, String profileImage, String nickname, int level, Double starRating, String content) {
        this.reviewId = reviewId;
        this.userId = userId;
        this.profileImage = profileImage;
        this.nickname = nickname;
        this.level = level;
        this.starRating = starRating;
        this.content = content;
    }
}

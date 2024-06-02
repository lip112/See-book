package com.example.seebook.domain.review.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProfileReviewResponseDTO {
    private String profileImage;
    private int level;
    private int levelCount;
    private int totalReviewCount;
    private int endPage;
    private List<Review> review;


    public ProfileReviewResponseDTO(String profileImage, int level, int levelCount, int totalReviewCount, int endPage, List<Review> review) {
        this.profileImage = profileImage;
        this.level = level;
        this.levelCount = levelCount;
        this.totalReviewCount = totalReviewCount;
        this.endPage = endPage;
        this.review = review;
    }

    @Builder
    private static class Review {
        private Long userId;
        private String profileImage;
        private String nickname;
        private int level;
        private Long starRating;
        private int content;
    }

}

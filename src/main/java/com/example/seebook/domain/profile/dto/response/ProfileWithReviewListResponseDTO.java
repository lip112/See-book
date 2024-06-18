package com.example.seebook.domain.profile.dto.response;

import com.example.seebook.domain.review.dto.ReviewDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class ProfileWithReviewListResponseDTO {
    private String profileImage;
    private String nickname;
    private int level;
    private int levelCount;
    private int totalReviewCount;
    private int endPage;

    private List<ReviewDTO> reviewList;
    @Builder
    public ProfileWithReviewListResponseDTO(String profileImage, String nickname, int level, int levelCount, int totalReviewCount, int endPage, List<ReviewDTO> reviewList) {
        this.profileImage = profileImage;
        this.nickname = nickname;
        this.level = level;
        this.levelCount = levelCount;
        this.totalReviewCount = totalReviewCount;
        this.endPage = endPage;
        this.reviewList = reviewList;
    }


}

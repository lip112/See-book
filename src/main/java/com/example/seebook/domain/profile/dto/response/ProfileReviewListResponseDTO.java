package com.example.seebook.domain.profile.dto.response;

import com.example.seebook.domain.profile.dto.AnotherProfileReviewDTO;
import com.example.seebook.domain.review.dto.ReviewDTO;
import lombok.Builder;

import java.util.List;

public class ProfileReviewListResponseDTO {
    private String profileImage;
    private String nickname;
    private int level;
    private int levelCount;
    private Long totalReviewCount;
    private Long endPage;

    private List<AnotherProfileReviewDTO> reviewList;
    @Builder
    public ProfileReviewListResponseDTO(String profileImage, String nickname, int level, int levelCount, Long totalReviewCount, Long endPage, List<AnotherProfileReviewDTO> reviewList) {
        this.profileImage = profileImage;
        this.nickname = nickname;
        this.level = level;
        this.levelCount = levelCount;
        this.totalReviewCount = totalReviewCount;
        this.endPage = endPage;
        this.reviewList = reviewList;
    }


}

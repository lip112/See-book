package com.example.seebook.domain.profile.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ProfileReviewDTO {
    private String bookId;
    private String cover;
    private String title;
    private String avgStar;
    private String totalReviewCount;
    private String content;

    @Builder
    public ProfileReviewDTO(String bookId, String cover, String title, String avgStar, String totalReviewCount, String content) {
        this.bookId = bookId;
        this.cover = cover;
        this.title = title;
        this.avgStar = avgStar;
        this.totalReviewCount = totalReviewCount;
        this.content = content;
    }
}

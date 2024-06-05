package com.example.seebook.domain.review.dto.response;

import com.example.seebook.domain.review.dto.ReviewDTO;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BookInReviewListResponseDTO {
    private int reviewCount;
    private int endPage;
    private List<ReviewDTO> review;


    @Builder
    public BookInReviewListResponseDTO(int reviewCount, int endPage, List<ReviewDTO> review) {
        this.reviewCount = reviewCount;
        this.endPage = endPage;
        this.review = review;
    }
}

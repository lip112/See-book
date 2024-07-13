package com.example.seebook.domain.review.dto.response;

import com.example.seebook.domain.review.dto.ReviewDTO;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class HomeReviewListResponseDTO {
    private List<ReviewDTO> review;

    @Builder
    public HomeReviewListResponseDTO(List<ReviewDTO> review) {
        this.review = review;
    }
}

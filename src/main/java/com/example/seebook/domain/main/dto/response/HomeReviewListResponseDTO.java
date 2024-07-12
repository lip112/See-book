package com.example.seebook.domain.main.dto.response;

import com.example.seebook.domain.book.dto.BookDTO;
import com.example.seebook.domain.review.dto.ReviewDTO;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
class HomeReviewListResponseDTO {
    private List<ReviewDTO> review;

    @Builder
    public HomeReviewListResponseDTO(List<ReviewDTO> review) {
        this.review = review;
    }
}

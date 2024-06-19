package com.example.seebook.domain.review.dto.response;

import com.example.seebook.domain.review.dto.ProfileReviewDTO;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class ProfileReviewResponseDTO {
    private Long totalReviewCont;
    private Long endPage;
    private List<ProfileReviewDTO> review;

    @Builder
    public ProfileReviewResponseDTO(Long totalReviewCont, Long endPage, List<ProfileReviewDTO> review) {
        this.totalReviewCont = totalReviewCont;
        this.endPage = endPage;
        this.review = review;
    }
}

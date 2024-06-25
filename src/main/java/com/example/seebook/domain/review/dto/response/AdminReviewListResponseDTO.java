package com.example.seebook.domain.review.dto.response;

import com.example.seebook.domain.review.dto.AdminReviewListDTO;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class AdminReviewListResponseDTO {
    private Long totalReviewCount;
    private Long endPage;
    private List<AdminReviewListDTO> review;

    @Builder
    public AdminReviewListResponseDTO(Long totalReviewCount, Long endPage, List<AdminReviewListDTO> review) {
        this.totalReviewCount = totalReviewCount;
        this.endPage = endPage;
        this.review = review;
    }
}

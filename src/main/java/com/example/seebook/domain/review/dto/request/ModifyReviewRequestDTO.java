package com.example.seebook.domain.review.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ModifyReviewRequestDTO {
    private Long userId;
    private Long reviewId;
    private Double starRating;
    private String content;
}

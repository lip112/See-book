package com.example.seebook.domain.review.dto.request;

import lombok.Getter;

@Getter
public class DeleteReviewRequestDTO {

    private Long userId;
    private Long reviewId;
}

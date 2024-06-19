package com.example.seebook.domain.review.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class WriteReviewRequestDTO {
    private Long userId;
    private Long bookId;
    private Double starRating;
    private String content;
}

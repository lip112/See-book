package com.example.seebook.domain.review.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ModifyReviewRequestDTO {
    @NotNull
    private Long userId;
    @NotNull
    private Long reviewId;
    @NotNull
    private Double starRating;
    @NotBlank
    private String content;
}

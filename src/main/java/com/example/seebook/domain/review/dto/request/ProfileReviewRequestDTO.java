package com.example.seebook.domain.review.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class ProfileReviewRequestDTO {
    @NotBlank
    private Long userId;

    @NotBlank
    private int page;
}

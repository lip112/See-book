package com.example.seebook.domain.review.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class ProfileReviewRequestDTO {
    @NotNull
    private Long userId;

    @NotNull
    private int page;
}

package com.example.seebook.domain.review.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class DeleteReviewRequestDTO {

    @NotNull
    private Long reviewId;
}

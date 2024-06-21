package com.example.seebook.domain.profile.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProfileReviewListRequestDTO {
    @NotNull
    private Long userId;
    @NotNull
    private int page;
}

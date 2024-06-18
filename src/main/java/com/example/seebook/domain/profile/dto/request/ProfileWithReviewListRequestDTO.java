package com.example.seebook.domain.profile.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProfileWithReviewListRequestDTO {
    private Long userId;
    private int page;
}

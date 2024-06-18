package com.example.seebook.domain.wishlist.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class GetWishlistRequestDTO {
    @NotBlank
    private Long userId;

    @NotBlank
    private int page;
}

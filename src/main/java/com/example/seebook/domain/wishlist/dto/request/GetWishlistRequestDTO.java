package com.example.seebook.domain.wishlist.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class GetWishlistRequestDTO {
    @NotNull
    private Long userId;

    @NotNull
    private int page;
}

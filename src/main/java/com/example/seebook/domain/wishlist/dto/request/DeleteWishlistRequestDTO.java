package com.example.seebook.domain.wishlist.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class DeleteWishlistRequestDTO {
    @NotBlank
    private Long wishlistId;

    @NotBlank
    private Long userId;

    @NotBlank
    private Long bookId;
}

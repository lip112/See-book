package com.example.seebook.domain.wishlist.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class DeleteWishlistRequestDTO {
    @NotNull
    private Long bookId;
}

package com.example.seebook.domain.wishlist.dto.request;

import com.example.seebook.domain.book.dto.BookDTO;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class AddWishlistRequestDTO {
    @NotBlank
    private BookDTO book;
    @NotBlank
    private Long userId;
}

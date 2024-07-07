package com.example.seebook.domain.wishlist.dto.request;

import com.example.seebook.domain.book.dto.BookDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class AddWishlistRequestDTO {
    @NotNull
    private Long bookId;

}

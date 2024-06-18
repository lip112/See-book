package com.example.seebook.domain.wishlist.dto.response;

import com.example.seebook.domain.book.dto.BookDTO;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class GetWishlistResponseDTO {
    private List<BookDTO> book;
    private Long endPage;
    private Long totalWishlistCount;

    @Builder
    public GetWishlistResponseDTO(List<BookDTO> book, Long endPage, Long totalWishlistCount) {
        this.book = book;
        this.endPage = endPage;
        this.totalWishlistCount = totalWishlistCount;
    }
}

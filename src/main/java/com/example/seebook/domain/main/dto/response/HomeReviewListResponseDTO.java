package com.example.seebook.domain.main.dto.response;

import com.example.seebook.domain.book.dto.BookDTO;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
class HomeReviewListResponseDTO {
    private List<BookDTO> review;

    @Builder
    public HomeReviewListResponseDTO(List<BookDTO> review) {
        this.review = review;
    }
}

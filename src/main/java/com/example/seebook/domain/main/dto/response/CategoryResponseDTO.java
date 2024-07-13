package com.example.seebook.domain.main.dto.response;

import com.example.seebook.domain.book.dto.BookDTO;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class CategoryResponseDTO {
    private Long totalCategoryCount;
    private Long endPage;
    private List<BookDTO> book;

    @Builder
    public CategoryResponseDTO(Long totalCategoryCount, Long endPage, List<BookDTO> book) {
        this.totalCategoryCount = totalCategoryCount;
        this.endPage = endPage;
        this.book = book;
    }
}

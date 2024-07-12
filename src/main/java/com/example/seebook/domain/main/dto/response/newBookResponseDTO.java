package com.example.seebook.domain.main.dto.response;

import com.example.seebook.domain.book.dto.BookDTO;
import lombok.Builder;

import java.util.List;

public class newBookResponseDTO {
    private Long totalBookCount;
    private int endPage;
    private List<BookDTO> book;

    @Builder
    public newBookResponseDTO(Long totalBookCount, int endPage, List<BookDTO> book) {
        this.totalBookCount = totalBookCount;
        this.endPage = endPage;
        this.book = book;
    }
}

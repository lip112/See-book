package com.example.seebook.domain.main.dto.response;

import com.example.seebook.domain.book.dto.BookDTO;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class NewBookResponseDTO {
    private Long totalBookCount;
    private int endPage;
    private List<BookDTO> book;

    @Builder
    public NewBookResponseDTO(Long totalBookCount, int endPage, List<BookDTO> book) {
        this.totalBookCount = totalBookCount;
        this.endPage = endPage;
        this.book = book;
    }
}

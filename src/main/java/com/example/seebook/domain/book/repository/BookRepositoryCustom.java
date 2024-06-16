package com.example.seebook.domain.book.repository;

import com.example.seebook.domain.book.dto.BookDTO;
import com.example.seebook.domain.book.dto.response.BookListResponseDTO;

public interface BookRepositoryCustom {

    BookListResponseDTO getBooksReviewSummary(BookListResponseDTO bookList);
    BookDTO getBooksReviewSummary(BookDTO bookDTO);
}

package com.example.seebook.domain.book.repository;

import com.example.seebook.domain.book.dto.BookDTO;
import com.example.seebook.domain.book.dto.response.BookListResponseDTO;
import com.example.seebook.domain.main.dto.response.CategoryResponseDTO;
import com.example.seebook.domain.main.dto.response.JoinMainPageResponseDTO;
import com.example.seebook.domain.main.dto.response.NewBookResponseDTO;

import java.util.List;

public interface BookRepositoryCustom {

    BookListResponseDTO getBooksReviewSummary(BookListResponseDTO bookList);
    BookDTO getBooksReviewSummary(BookDTO bookDTO);
    List<JoinMainPageResponseDTO.BookWithReview> getBestBooks();
    CategoryResponseDTO findCategoryBooks(String categoryName, int offset, int limit);

    NewBookResponseDTO getNewBooks(int offset, int limit);
}

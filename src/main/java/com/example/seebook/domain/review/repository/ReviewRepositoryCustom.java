package com.example.seebook.domain.review.repository;

import com.example.seebook.domain.book.domain.Book;
import com.example.seebook.domain.book.dto.BookDTO;
import com.example.seebook.domain.review.dto.response.BookInReviewListResponseDTO;

import java.awt.print.Pageable;

public interface ReviewRepositoryCustom {

    BookInReviewListResponseDTO getReviewList(BookDTO bookDTO, int page, int offset, int limit);
}

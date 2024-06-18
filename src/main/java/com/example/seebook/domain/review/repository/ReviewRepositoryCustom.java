package com.example.seebook.domain.review.repository;

import com.example.seebook.domain.book.dto.BookDTO;
import com.example.seebook.domain.book.dto.response.BookInReviewListResponseDTO;

public interface ReviewRepositoryCustom {

    BookInReviewListResponseDTO getReviewList(BookDTO bookDTO, int page, int offset, int limit);
}

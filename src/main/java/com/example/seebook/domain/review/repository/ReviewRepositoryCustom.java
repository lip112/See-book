package com.example.seebook.domain.review.repository;

import com.example.seebook.domain.book.dto.BookDTO;
import com.example.seebook.domain.book.dto.response.BookInReviewListResponseDTO;
import com.example.seebook.domain.review.dto.response.AdminReviewListResponseDTO;
import com.example.seebook.domain.review.dto.response.HomeReviewListResponseDTO;
import com.example.seebook.domain.review.dto.response.ProfileReviewResponseDTO;

public interface ReviewRepositoryCustom {

    BookInReviewListResponseDTO getBookInReviewList(BookDTO bookDTO, int offset, int limit);

    ProfileReviewResponseDTO getProfileReviewList(Long userId, int offset, int limit);

    AdminReviewListResponseDTO getAdminReviewList(int offset, int limit, String queryType, String query);
}

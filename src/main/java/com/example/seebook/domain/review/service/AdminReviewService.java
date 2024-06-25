package com.example.seebook.domain.review.service;

import com.example.seebook.domain.review.dto.response.AdminReviewListResponseDTO;
import com.example.seebook.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminReviewService {
    private final ReviewRepository reviewRepository;

    public AdminReviewListResponseDTO getReviewList(int page, String queryType, String query) {
        return reviewRepository.getAdminReviewList((page-1)*10, page*10-1, queryType, query);
    }

    public void deleteReview(Long reviewId) {
        reviewRepository.deleteById(reviewId);
    }
}

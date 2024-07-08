package com.example.seebook.domain.review.service;

import com.example.seebook.domain.book.domain.Book;
import com.example.seebook.domain.book.dto.BookDTO;
import com.example.seebook.domain.review.domain.Review;
import com.example.seebook.domain.review.dto.request.ModifyReviewRequestDTO;
import com.example.seebook.domain.review.dto.request.WriteReviewRequestDTO;
import com.example.seebook.domain.book.dto.response.BookInReviewListResponseDTO;
import com.example.seebook.domain.review.dto.response.ProfileReviewResponseDTO;
import com.example.seebook.domain.review.repository.ReviewRepository;
import com.example.seebook.domain.user.domain.User;
import com.example.seebook.global.exception.ReviewException;
import com.example.seebook.global.jwt.UserAuthorizationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    public void writeReview(WriteReviewRequestDTO writeReviewRequestDTO, Book book, User user) {
        reviewRepository.save(Review.builder()
                        .user(user)
                        .book(book)
                        .nickname(user.getNickname())
                        .content(writeReviewRequestDTO.getContent())
                        .starRating(writeReviewRequestDTO.getStarRating())
                .build());
    }

    public void modifyReview(ModifyReviewRequestDTO modifyReviewRequestDTO) {
        Review review = reviewRepository.findById(modifyReviewRequestDTO.getReviewId())
                .orElseThrow(ReviewException.NotFoundReviewIdException::new);
        if (review.getUser().getUserId() != UserAuthorizationUtil.getLoginUserId())
            throw new ReviewException.NotMatchUserException();
        review.changeContent(modifyReviewRequestDTO.getContent());
        review.changeStarRating(modifyReviewRequestDTO.getStarRating());
        reviewRepository.save(review);
    }

    @Transactional
    public void deleteReview(Long reviewId, User user) {
        reviewRepository.deleteByReviewIdAndUser(reviewId, user);
    }

    public BookInReviewListResponseDTO getBookInReviewList(BookDTO bookDTO, int page) { // page?
        return reviewRepository.getBookInReviewList(bookDTO, page, (page-1)*10, page*10-1);
        // 1 : 0 , 9
        // 2 : 10 , 19
        // 3 : 20 , 29
    }

    public ProfileReviewResponseDTO getProfileReviewList(int page, Long userId) {
        return reviewRepository.getProfileReviewList(userId,
                (page-1)*10, page*10-1);
    }

    public void GetHomeReviewList() {

    }

    public Review findById(Long reviewId) {
        return reviewRepository.findById(reviewId)
                .orElseThrow(ReviewException.NotFoundReviewIdException::new);
    }

}

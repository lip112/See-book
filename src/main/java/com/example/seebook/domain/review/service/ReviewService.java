package com.example.seebook.domain.review.service;

import com.example.seebook.domain.book.domain.Book;
import com.example.seebook.domain.book.service.BookService;
import com.example.seebook.domain.review.domain.Review;
import com.example.seebook.domain.review.dto.request.ModifyReviewRequestDTO;
import com.example.seebook.domain.review.dto.request.ReportReviewRequestDTO;
import com.example.seebook.domain.review.dto.request.WriteReviewRequestDTO;
import com.example.seebook.domain.review.dto.response.BookInReviewListResponseDTO;
import com.example.seebook.domain.review.repository.ReviewRepository;
import com.example.seebook.domain.user.service.UserService;
import com.example.seebook.global.exception.ReviewException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final UserService userService;
    private final BookService bookService;
    public void writeReview(WriteReviewRequestDTO writeReviewRequestDTO) {
        reviewRepository.save(Review.builder()
                        .user(userService.getById(writeReviewRequestDTO.getUserId()))
                        .book(bookService.getById(writeReviewRequestDTO.getBookId()))
                        .content(writeReviewRequestDTO.getContent())
                        .starRating(writeReviewRequestDTO.getStarRating())
                .build());
    }

    public void modifyReview(ModifyReviewRequestDTO modifyReviewRequestDTO) {
        Review review = reviewRepository.findById(modifyReviewRequestDTO.getReviewId())
                .orElseThrow(ReviewException.NotFoundReviewIdException::new);
        review.changeContent(modifyReviewRequestDTO.getContent());
        review.changeStarRating(modifyReviewRequestDTO.getStarRating());
        reviewRepository.save(review);
    }

    public void ReportReview(ReportReviewRequestDTO reportReviewRequestDTO) {
//        Report에다가 세이브해야함
    }

    public void DeleteReview(Long userId) {
        reviewRepository.deleteById(userId);
    }

    public BookInReviewListResponseDTO getReviewList(Book book, int page) { // page?
        return reviewRepository.getReviewList(book, page, (page-1)*10, page*10-1);
        // 1 : 0 , 9
        // 2 : 10 , 19
        // 3 : 20 , 29
    }
}

package com.example.seebook.domain.review.controller;

import com.example.seebook.domain.book.domain.Book;
import com.example.seebook.domain.book.service.BookService;
import com.example.seebook.domain.level.service.LevelService;
import com.example.seebook.domain.review.dto.request.DeleteReviewRequestDTO;
import com.example.seebook.domain.review.dto.request.ModifyReviewRequestDTO;
import com.example.seebook.domain.review.dto.request.WriteReviewRequestDTO;
import com.example.seebook.domain.review.dto.response.ProfileReviewResponseDTO;
import com.example.seebook.domain.review.service.ReviewService;
import com.example.seebook.domain.user.domain.User;
import com.example.seebook.domain.user.service.UserService;
import com.example.seebook.global.jwt.UserAuthorizationUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/review")
public class ReviewController {
    private final ReviewService reviewService;
    private final LevelService levelService;
    private final UserService userService;
    private final BookService bookService;

    @PostMapping("/write")
    public ResponseEntity<?> register(@Valid @RequestBody WriteReviewRequestDTO writeRequestDTO) {
        Long userId = UserAuthorizationUtil.getLoginUserId();
        User user = userService.findById(userId);
        Book book = bookService.findById(writeRequestDTO.getBookId());
        reviewService.writeReview(writeRequestDTO, book, user);
        levelService.plusLevelCount(userId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }

    @PutMapping("/modify")
    public ResponseEntity<?> modify(@Valid @RequestBody ModifyReviewRequestDTO modifyReviewRequestDTO) {
        reviewService.modifyReview(modifyReviewRequestDTO);
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@Valid @RequestBody DeleteReviewRequestDTO deleteReviewRequestDTO) {
        Long userId = UserAuthorizationUtil.getLoginUserId();
        User user = userService.findById(userId);
        reviewService.deleteReview(deleteReviewRequestDTO.getReviewId(), user);
        levelService.minusLevelCount(userId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }

    @GetMapping("/list")
    public ResponseEntity<ProfileReviewResponseDTO> getProfileList(@RequestParam("page") int page) {
        Long userId = UserAuthorizationUtil.getLoginUserId();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(reviewService.getProfileReviewList(page, userId));
    }
}

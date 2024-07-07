package com.example.seebook.domain.review.controller;

import com.example.seebook.domain.level.service.LevelService;
import com.example.seebook.domain.review.dto.request.DeleteReviewRequestDTO;
import com.example.seebook.domain.review.dto.request.ModifyReviewRequestDTO;
import com.example.seebook.domain.review.dto.request.ProfileReviewRequestDTO;
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

    @PostMapping("/write")
    public ResponseEntity<?> register(@Valid @RequestBody WriteReviewRequestDTO writeRequestDTO) {
        System.out.println("실생1");
        Long userId = 2L;
        reviewService.writeReview(writeRequestDTO, userId);
        System.out.println("실생2");
        levelService.plusLevelCount(userId);
        System.out.println("실생3");
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
        User user = userService.findById(UserAuthorizationUtil.getLoginUserId());
        reviewService.deleteReview(deleteReviewRequestDTO.getReviewId(), user);
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }

    @PostMapping("/list")
    public ResponseEntity<ProfileReviewResponseDTO> getProfileList(@Valid @RequestBody ProfileReviewRequestDTO profileReviewRequestDTO) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(reviewService.getProfileReviewList(profileReviewRequestDTO));
    }
}

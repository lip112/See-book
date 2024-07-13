package com.example.seebook.domain.main.controller;

import com.example.seebook.domain.review.dto.response.HomeReviewListResponseDTO;
import com.example.seebook.domain.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/main/home")
public class HomeController {
    private final ReviewService reviewService;

    @GetMapping("/review-list")
    public ResponseEntity<HomeReviewListResponseDTO> getHomeReviewList(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(reviewService.getHomeReviewList());
    }

}

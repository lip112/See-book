package com.example.seebook.domain.review.controller;


import com.example.seebook.domain.review.service.AdminReviewService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/review")
public class AdminReviewController {
    private final AdminReviewService adminReviewService;

    @GetMapping("/list")
    public ResponseEntity<?> getReviewList(@RequestParam(value = "page") int page,
                                           @RequestParam(value = "queryType") String queryType,
                                           @RequestParam(value = "query") String query) {
        return ResponseEntity
                .ok()
                .body(adminReviewService.getReviewList(page, queryType, query));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteReview(@RequestParam(value = "reviewId") Long reviewId) {
        adminReviewService.deleteReview(reviewId);
        return ResponseEntity
                .ok()
                .build();
    }
}

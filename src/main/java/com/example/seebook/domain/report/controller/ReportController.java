package com.example.seebook.domain.report.controller;

import com.example.seebook.domain.report.dto.request.ReportReviewRequestDTO;
import com.example.seebook.domain.report.service.ReportService;
import com.example.seebook.domain.review.domain.Review;
import com.example.seebook.domain.review.service.ReviewService;
import com.example.seebook.domain.user.domain.User;
import com.example.seebook.domain.user.service.UserService;
import com.example.seebook.global.jwt.UserAuthorizationUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/report")
public class ReportController {
    private final ReportService reportService;
    private final UserService userService;
    private final ReviewService reviewService;

    @PostMapping("/request-review")
    public ResponseEntity<Void> reportReview(@Valid @RequestBody ReportReviewRequestDTO reportReviewRequestDTO) {
        User reporter = userService.findById(UserAuthorizationUtil.getLoginUserId());
        User reportred = userService.findById(reportReviewRequestDTO.getReportedId());
        Review review = reviewService.findById(reportReviewRequestDTO.getReviewId());
        reportService.reportReview(reportReviewRequestDTO, reporter, reportred, review);

        return ResponseEntity.ok().build();
    }
}

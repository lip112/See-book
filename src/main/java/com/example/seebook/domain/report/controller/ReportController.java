package com.example.seebook.domain.report.controller;

import com.example.seebook.domain.report.dto.request.ReportReviewRequestDTO;
import com.example.seebook.domain.report.service.ReportService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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

    @PostMapping("/review")
    public void reportReview(@Valid @RequestBody ReportReviewRequestDTO reportReviewRequestDTO) {
        reportService.reportReview(reportReviewRequestDTO);
    }
}

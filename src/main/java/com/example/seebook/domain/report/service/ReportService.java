package com.example.seebook.domain.report.service;

import com.example.seebook.domain.report.domain.Report;
import com.example.seebook.domain.report.dto.request.ReportReviewRequestDTO;
import com.example.seebook.domain.report.dto.response.AdminReportDetailResponseDTO;
import com.example.seebook.domain.report.repository.ReportRepository;
import com.example.seebook.domain.review.repository.ReviewRepository;
import com.example.seebook.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ReportService {
    private final ReportRepository reportRepository;
    private final UserRepository userRepository;
    private final ReviewRepository reviewRepository;
    public void reportReview(ReportReviewRequestDTO reportReviewRequestDTO) {
        reportRepository.save(Report.builder()
                        .reviewId(reviewRepository.findById(reportReviewRequestDTO.getReviewId()).get())
                        .reporterId(userRepository.findById(reportReviewRequestDTO.getReporterId()).get())
                        .reportedId(userRepository.findById(reportReviewRequestDTO.getReportedId()).get())
                        .reportType(reportReviewRequestDTO.getReportType())
                        .description(reportReviewRequestDTO.getDescription())
                        .isProcessed(false)
                        .reportDate(LocalDateTime.now())
                .build());
    }


}

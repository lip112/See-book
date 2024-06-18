package com.example.seebook.domain.report.service;

import com.example.seebook.domain.report.domain.Report;
import com.example.seebook.domain.report.domain.ReportStatus;
import com.example.seebook.domain.report.dto.request.ReportReviewRequestDTO;
import com.example.seebook.domain.report.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ReportService {
    private final ReportRepository reportRepository;
    public void reportReview(ReportReviewRequestDTO reportReviewRequestDTO) {
        reportRepository.save(Report.builder()
                        .reviewId(reportReviewRequestDTO.getReviewId())
                        .reporterId(reportReviewRequestDTO.getReporterId())
                        .reportedId(reportReviewRequestDTO.getReportedId())
                        .reportType(reportReviewRequestDTO.getReportType())
                        .description(reportReviewRequestDTO.getDescription())
                        .isActive(ReportStatus.PENDING)
                        .reportDate(LocalDateTime.now())
                .build());
    }
}

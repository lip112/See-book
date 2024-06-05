package com.example.seebook.domain.report.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReportReviewRequestDTO {
    private Long reporterId;
    private Long reportedId;
    private Long reviewId;
    private String reportType;
    private String description;
    @Builder
    public ReportReviewRequestDTO(Long reporterId, Long reportedId, Long reviewId, String reportType, String description) {
        this.reporterId = reporterId;
        this.reportedId = reportedId;
        this.reviewId = reviewId;
        this.reportType = reportType;
        this.description = description;
    }
}

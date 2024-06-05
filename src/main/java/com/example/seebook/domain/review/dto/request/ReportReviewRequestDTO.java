package com.example.seebook.domain.review.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReportReviewRequestDTO {
    private Long reporterId;
    private Long reportedId;
    private Long reviewIid;
    private Long reportType;
    private String description;

    @Builder
    public ReportReviewRequestDTO(Long reporterId, Long reportedId, Long reviewIid, Long reportType, String description) {
        this.reporterId = reporterId;
        this.reportedId = reportedId;
        this.reviewIid = reviewIid;
        this.reportType = reportType;
        this.description = description;
    }
}

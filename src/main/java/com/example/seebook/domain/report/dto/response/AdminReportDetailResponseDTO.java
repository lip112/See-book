package com.example.seebook.domain.report.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class AdminReportDetailResponseDTO {
    private Long reportId;
    private String reporterNickname;
    private Long reportedId;
    private String reportedNickname;
    private String reportType;
    private String reportDate;
    private String description;

    private Long reviewId;
    private String profileIamge;
    private Double starRating;
    private String content;

    @Builder
    public AdminReportDetailResponseDTO(Long reportId, String reporterNickname, Long reportedId, String reportedNickname, String reportType, String reportDate, String description, Long reviewId, String profileIamge, Double starRating, String content) {
        this.reportId = reportId;
        this.reporterNickname = reporterNickname;
        this.reportedId = reportedId;
        this.reportedNickname = reportedNickname;
        this.reportType = reportType;
        this.reportDate = reportDate;
        this.description = description;
        this.reviewId = reviewId;
        this.profileIamge = profileIamge;
        this.starRating = starRating;
        this.content = content;
    }
}

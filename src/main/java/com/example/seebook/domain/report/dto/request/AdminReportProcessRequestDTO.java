package com.example.seebook.domain.report.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
public class AdminReportProcessRequestDTO {
    private Long reportId;
    private Long reportedId;
    private Long reviewId;
    private Long suspensionPeriod;
    private String reportType;
    private boolean resetProfileImage;
	private boolean resetNickname;
	private boolean deleteReview;

    @Builder
    public AdminReportProcessRequestDTO(Long reportId, Long reportedId, Long reviewId, Long suspensionPeriod, String reportType, boolean resetProfileImage, boolean resetNickname, boolean deleteReview) {
        this.reportId = reportId;
        this.reportedId = reportedId;
        this.reviewId = reviewId;
        this.suspensionPeriod = suspensionPeriod;
        this.reportType = reportType;
        this.resetProfileImage = resetProfileImage;
        this.resetNickname = resetNickname;
        this.deleteReview = deleteReview;
    }
}

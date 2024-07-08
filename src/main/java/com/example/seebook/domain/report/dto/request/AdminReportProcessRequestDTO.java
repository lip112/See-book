package com.example.seebook.domain.report.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;

@Getter
public class AdminReportProcessRequestDTO {
    @NotNull
    private Long reportId;
    @NotNull
    private Long reportedId;
    @NotNull
    private Long reviewId;
    @NotNull
    private Long suspensionPeriod;
    @NotNull
    private String reportType;
    @NotNull
    private boolean resetProfileImage;
    @NotNull
	private boolean resetNickname;
    @NotNull
	private boolean deleteReview;
}

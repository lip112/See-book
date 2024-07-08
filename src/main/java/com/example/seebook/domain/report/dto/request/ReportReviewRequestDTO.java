package com.example.seebook.domain.report.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReportReviewRequestDTO {
    @NotNull
    private Long reportedId;
    @NotNull
    private Long reviewId;
    @NotBlank
    private String reportType;
    @NotBlank
    private String description;
    @Builder
    public ReportReviewRequestDTO(Long reportedId, Long reviewId, String reportType, String description) {
        this.reportedId = reportedId;
        this.reviewId = reviewId;
        this.reportType = reportType;
        this.description = description;
    }
}

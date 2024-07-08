package com.example.seebook.domain.report.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ReportDTO {
    private Long reportId;
    private Long reporterId;
    private String reporterNickname;
    private Long reportedId;
    private String reportedNickname;
    private String reportType;

    private LocalDateTime reportDate;
    private String description;
    private boolean isProcessed;

    @Builder
    public ReportDTO(Long reportId, Long reporterId, String reporterNickname, Long reportedId, String reportedNickname, String reportType, LocalDateTime reportDate, String description, boolean isProcessed) {
        this.reportId = reportId;
        this.reporterId = reporterId;
        this.reporterNickname = reporterNickname;
        this.reportedId = reportedId;
        this.reportedNickname = reportedNickname;
        this.reportType = reportType;
        this.reportDate = reportDate;
        this.description = description;
        this.isProcessed = isProcessed;
    }


    public void changeProcess(boolean processed) {
        isProcessed = processed;
    }
}

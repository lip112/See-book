package com.example.seebook.domain.report.dto.response;

import com.example.seebook.domain.report.dto.ReportDTO;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class AdminReportListResponseDTO {
    private Long totalReportCount;
    private Long endPage;
    private List<ReportDTO> report;

    @Builder
    public AdminReportListResponseDTO(Long totalReportCount, Long endPage, List<ReportDTO> report) {
        this.totalReportCount = totalReportCount;
        this.endPage = endPage;
        this.report = report;
    }
}

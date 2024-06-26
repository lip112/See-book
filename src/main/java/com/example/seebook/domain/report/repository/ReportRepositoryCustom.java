package com.example.seebook.domain.report.repository;

import com.example.seebook.domain.report.dto.response.AdminReportDetailResponseDTO;
import com.example.seebook.domain.report.dto.response.AdminReportListResponseDTO;

public interface ReportRepositoryCustom {

    AdminReportListResponseDTO getAdminReportList(int offset, int limit, String query, String queryType);

    AdminReportDetailResponseDTO getAdminReportDetail(Long reportId);
}

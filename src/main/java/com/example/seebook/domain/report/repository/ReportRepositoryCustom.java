package com.example.seebook.domain.report.repository;

import com.example.seebook.domain.report.dto.response.AdminReportDetailResponseDTO;
import com.example.seebook.domain.report.dto.response.AdminReportListResponseDTO;

public interface ReportRepositoryCustom {

    AdminReportListResponseDTO getAdminReportList(int offset, int limit, String queryType, String query);

    AdminReportDetailResponseDTO getAdminReportDetail(Long reportId);
}

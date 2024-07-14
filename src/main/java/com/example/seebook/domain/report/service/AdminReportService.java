package com.example.seebook.domain.report.service;

import com.example.seebook.domain.report.domain.Report;
import com.example.seebook.domain.report.dto.request.AdminDeleteReportRequestDTO;
import com.example.seebook.domain.report.dto.request.AdminReportProcessRequestDTO;
import com.example.seebook.domain.report.dto.response.AdminReportDetailResponseDTO;
import com.example.seebook.domain.report.dto.response.AdminReportListResponseDTO;
import com.example.seebook.domain.report.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AdminReportService {
    private final ReportRepository reportRepository;

    public AdminReportListResponseDTO getReportList(int page, String queryType, String query) {
        return reportRepository.getAdminReportList((page-1)*10, page*10-1, queryType, query);
    }
    public AdminReportDetailResponseDTO getReportDetail(Long reportId) {
        return reportRepository.getAdminReportDetail(reportId);
    }

    public void processReport(AdminReportProcessRequestDTO adminReportProcessRequestDTO) {
        Report report = reportRepository.findById(adminReportProcessRequestDTO.getReportId())
                .orElseThrow(() -> new IllegalArgumentException("해당 신고가 존재하지 않습니다."));

        report.changeProcessed(true);
        reportRepository.save(report);
    }

    @Transactional
    public void deleteReport(AdminDeleteReportRequestDTO requestDTO) {
        reportRepository.deleteAllByIdInBatch(requestDTO.getReportId());
    }
}

package com.example.seebook.domain.report.dto.request;

import lombok.Getter;

import java.util.List;

@Getter
public class AdminDeleteReportRequestDTO {
    private List<Long> reportId;
}

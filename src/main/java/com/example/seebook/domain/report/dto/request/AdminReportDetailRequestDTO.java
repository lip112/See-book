package com.example.seebook.domain.report.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
public class AdminReportDetailRequestDTO {
    @NotNull
    private Long reportId;
}

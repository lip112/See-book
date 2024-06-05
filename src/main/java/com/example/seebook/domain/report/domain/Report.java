package com.example.seebook.domain.report.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reportId;

    private Long reviewId;
    private Long reporterId;
    private Long reportedId;
    private String reportType;
    private String description;
    private LocalDateTime reportDate;

    @Enumerated(EnumType.STRING)
    private ReportStatus isActive;
}

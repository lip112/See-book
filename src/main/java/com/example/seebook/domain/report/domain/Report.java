package com.example.seebook.domain.report.domain;

import com.example.seebook.domain.review.domain.Review;
import com.example.seebook.domain.user.domain.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
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

    @ManyToOne
    private Review reviewId;
    @ManyToOne
    private User reporterId;
    @ManyToOne
    private User reportedId;
    private String reportType;
    private String description;
    private LocalDateTime reportDate;
    private boolean isProcessed;

    @Builder
    public Report(Long reportId, Review reviewId, User reporterId, User reportedId, String reportType, String description, LocalDateTime reportDate, boolean isProcessed) {
        this.reportId = reportId;
        this.reviewId = reviewId;
        this.reporterId = reporterId;
        this.reportedId = reportedId;
        this.reportType = reportType;
        this.description = description;
        this.reportDate = reportDate;
        this.isProcessed = isProcessed;
    }

    public void changeProcessed(boolean processed) {
        isProcessed = processed;
    }
}

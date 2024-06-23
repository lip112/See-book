package com.example.seebook.domain.suspend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class SuspendDTO {
    private boolean isSuspended;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String reason;

    @Builder
    public SuspendDTO(LocalDateTime startDate, LocalDateTime endDate, String reason) {
        this.isSuspended = true;
        this.startDate = startDate;
        this.endDate = endDate;
        this.reason = reason;
    }
}

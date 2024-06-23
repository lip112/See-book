package com.example.seebook.domain.suspend.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class SuspendResponseDTO {
    private Long suspendId;

    private Long userId;

    private String reason;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private boolean isSuspend;
    @Builder
    public SuspendResponseDTO(Long suspendId, Long userId, String reason, LocalDateTime startDate, LocalDateTime endDate) {
        this.suspendId = suspendId;
        this.userId = userId;
        this.reason = reason;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}

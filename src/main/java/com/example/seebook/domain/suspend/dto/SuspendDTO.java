package com.example.seebook.domain.suspend.dto;

import com.example.seebook.domain.suspend.domain.Suspend;
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
    public SuspendDTO(boolean isSuspended, LocalDateTime startDate, LocalDateTime endDate, String reason) {
        this.isSuspended = isSuspended;
        this.startDate = startDate;
        this.endDate = endDate;
        this.reason = reason;
    }

    public static SuspendDTO from(Suspend suspend) {
        return SuspendDTO.builder()
                .isSuspended(LocalDateTime.now().isBefore(suspend.getEndDate()))
                .startDate(suspend.getStartDate())
                .endDate(suspend.getEndDate())
                .reason(suspend.getReason())
                .build();

    }

    public static SuspendDTO notSuspend() {
        return SuspendDTO.builder()
                .isSuspended(false)
                .build();
    }
}

package com.example.seebook.domain.suspend.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
public class Suspend {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long suspendId;

    private Long userId;

    private String reason;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    @Builder
    public Suspend(Long userId, String reason, LocalDateTime startDate, LocalDateTime endDate) {
        this.userId = userId;
        this.reason = reason;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public void changeDate(LocalDateTime startDate, LocalDateTime endTime) {
        this.startDate = startDate;
        this.endDate = endTime;
    }
}

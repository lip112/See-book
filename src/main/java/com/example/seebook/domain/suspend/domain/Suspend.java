package com.example.seebook.domain.suspend.domain;

import com.example.seebook.domain.user.domain.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
public class Suspend {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long suspendId;

    @OneToOne
    private User userId;

    private boolean isSuspended;
    private String reason;
    private LocalDateTime startDate;
    private LocalDateTime endTime;

    @Builder
    public Suspend(User userId, boolean isSuspended, String reason, LocalDateTime startDate, LocalDateTime endTime) {
        this.userId = userId;
        this.isSuspended = isSuspended;
        this.reason = reason;
        this.startDate = startDate;
        this.endTime = endTime;
    }

    public void changeDate(LocalDateTime startDate, LocalDateTime endTime) {
        this.startDate = startDate;
        this.endTime = endTime;
    }
}

package com.example.seebook.domain.user.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class Suspend {
    private boolean isSuspended;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String reason;
}

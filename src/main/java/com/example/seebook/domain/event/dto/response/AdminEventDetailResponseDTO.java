package com.example.seebook.domain.event.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class AdminEventDetailResponseDTO {
    private Long eventId;
    private String title;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String imageLink;
    private boolean isActivated;

    @Builder
    public AdminEventDetailResponseDTO(Long eventId, String title, LocalDateTime startDate, LocalDateTime endDate, String imageLink) {
        this.eventId = eventId;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.imageLink = imageLink;
        this.isActivated = LocalDateTime.now().isBefore(endDate);
    }
}

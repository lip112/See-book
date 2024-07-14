package com.example.seebook.domain.event.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class AdminEventListDTO {
    private Long eventId;
    private String title;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String imageLink;
    private boolean isActivated;

    @Builder
    public AdminEventListDTO(Long eventId, String title, LocalDateTime startDate, LocalDateTime endDate, String imageLink) {
        this.eventId = eventId;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.imageLink = imageLink;
        this.isActivated = LocalDateTime.now().isBefore(endDate);
    }


}

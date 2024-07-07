package com.example.seebook.domain.event.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class AdminEventListDTO {
    private Long eventId;
    private String title;
    private String startDate;
    private String endDate;
    private String imageUrl;
    private boolean isActivated;

    @Builder
    public AdminEventListDTO(Long eventId, String title, String startDate, String endDate, String imageUrl) {
        this.eventId = eventId;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.imageUrl = imageUrl;
        this.isActivated = LocalDateTime.now().isBefore(LocalDateTime.parse(endDate));
    }


}

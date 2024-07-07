package com.example.seebook.domain.event.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class EventDTO {
    private Long eventId;
    private String title;
    private String startDate;
    private String endDate;
    private String imageUrl;
    private boolean isActivated;

    @Builder
    public EventDTO(Long eventId, String title, String startDate, String endDate, String imageUrl, boolean isActivated) {
        this.eventId = eventId;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.imageUrl = imageUrl;
        this.isActivated = isActivated;
    }

    public void isEarlyStopped(boolean isEarlyStopped) {
        if (isEarlyStopped) {
            this.endDate = LocalDateTime.now().toString();
        }
    }

    public void changeImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}

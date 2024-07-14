package com.example.seebook.domain.event.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NegativeOrZero;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class EventDTO {
    private Long eventId;
    private String title;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String imageLink;
    private boolean isActivated;

    @Builder
    public EventDTO(Long eventId, String title, LocalDateTime startDate, LocalDateTime endDate, String imageLink, boolean isActivated) {
        this.eventId = eventId;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.imageLink = imageLink;
        this.isActivated = isActivated;
    }

    public void isEarlyStopped(boolean isEarlyStopped) {
        if (isEarlyStopped) {
            this.endDate = LocalDateTime.now();
        }
    }

    public void changeImageUrl(String imageUrl) {
        this.imageLink = imageUrl;
    }
}

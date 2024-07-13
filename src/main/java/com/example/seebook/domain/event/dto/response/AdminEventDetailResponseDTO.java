package com.example.seebook.domain.event.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class AdminEventDetailResponseDTO {
    private Long eventId;
    private String title;
    private String content;
    private String startDate;
    private String endDate;
    private String imageLink;
    private boolean isActivated;

    @Builder
    public AdminEventDetailResponseDTO(Long eventId, String title, String content, String startDate, String endDate, String imageLink) {
        this.eventId = eventId;
        this.title = title;
        this.content = content;
        this.startDate = startDate;
        this.endDate = endDate;
        this.imageLink = imageLink;
        this.isActivated = LocalDateTime.now().isBefore(LocalDateTime.parse(endDate));
    }
}

package com.example.seebook.domain.event.domain;

import com.example.seebook.domain.event.dto.EventDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Getter
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long eventId;

    private String title;

    private String content;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private String imageUrl;

    @Builder
    public Event(Long eventId, String title, String content, LocalDateTime startDate, LocalDateTime endDate, String imageUrl) {
        this.eventId = eventId;
        this.title = title;
        this.content = content;
        this.startDate = startDate;
        this.endDate = endDate;
        this.imageUrl = imageUrl;
    }

    public void changeEvent(EventDTO eventDTO) {
        this.title = eventDTO.getTitle();
        this.content = eventDTO.getContent();
        this.startDate = LocalDateTime.parse(eventDTO.getStartDate());
        this.endDate = LocalDateTime.parse(eventDTO.getEndDate());
        this.imageUrl = eventDTO.getImageUrl();
    }

}

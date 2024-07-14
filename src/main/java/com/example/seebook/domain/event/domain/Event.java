package com.example.seebook.domain.event.domain;

import com.example.seebook.domain.event.dto.EventDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long eventId;

    private String title;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private String imageLink;

    @Builder
    public Event(Long eventId, String title, LocalDateTime startDate, LocalDateTime endDate, String imageLink) {
        this.eventId = eventId;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.imageLink = imageLink;
    }

    public void changeEventContent(EventDTO eventDTO) {
        this.title = eventDTO.getTitle();
        this.startDate = eventDTO.getStartDate();
        this.endDate = eventDTO.getEndDate();
        if (eventDTO.getImageLink() != null){
            this.imageLink = eventDTO.getImageLink();
        }
    }

}

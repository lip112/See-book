package com.example.seebook.domain.event.service;

import com.example.seebook.domain.event.domain.Event;
import com.example.seebook.domain.event.dto.EventDTO;
import com.example.seebook.domain.event.dto.request.AdminEventModifyRequestDTO;
import com.example.seebook.domain.event.dto.response.AdminEventDetailResponseDTO;
import com.example.seebook.domain.event.dto.response.AdminEventListResponseDTO;
import com.example.seebook.domain.event.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AdminEventService {
    private final EventRepository eventRepository;

    public AdminEventListResponseDTO getAdminEventList(int page, String query, String queryType) {
        return eventRepository.getAdminEventList((page - 1) * 10, page*10 -1, query, queryType);
    }

    public AdminEventDetailResponseDTO getAdminEventDetail(Long eventId) {
        return eventRepository.getAdminEventDetail(eventId);
    }

    public void modifyEvent(EventDTO modifyRequestDTO) {
        Event event = eventRepository.findById(modifyRequestDTO.getEventId())
                .orElseThrow(() -> new IllegalArgumentException("해당 이벤트가 존재하지 않습니다."));

        event.changeEvent(modifyRequestDTO);
        eventRepository.save(event);
    }

    public void registerEvent(EventDTO eventDTO) {
        Event event = Event.builder()
                .title(eventDTO.getTitle())
                .content(eventDTO.getContent())
                .startDate(LocalDateTime.parse(eventDTO.getStartDate()))
                .endDate(LocalDateTime.parse(eventDTO.getEndDate()))
                .imageUrl(eventDTO.getImageUrl())
                .build();

        eventRepository.save(event);
    }

}

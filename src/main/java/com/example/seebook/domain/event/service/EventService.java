package com.example.seebook.domain.event.service;

import com.example.seebook.domain.event.dto.EventDTO;
import com.example.seebook.domain.event.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;

    public List<EventDTO> getMainEventList() {
        List<EventDTO> list = eventRepository.findByEndDateAfter(LocalDateTime.now())
                .stream()
                .map(event ->
                        EventDTO.builder()
                                .title(event.getTitle())
                                .imageLink(event.getImageLink())
                                .build())
                .toList();

        return list.isEmpty() ? Collections.emptyList() : list;
    }
}

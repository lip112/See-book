package com.example.seebook.domain.event.dto.request;

import com.example.seebook.domain.event.dto.EventDTO;
import lombok.Builder;
import lombok.Getter;

@Getter
public class AdminEventModifyRequestDTO {
    private EventDTO event;

    @Builder
    public AdminEventModifyRequestDTO(EventDTO event) {
        this.event = event;
    }
}

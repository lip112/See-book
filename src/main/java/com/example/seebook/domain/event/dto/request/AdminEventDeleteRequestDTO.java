package com.example.seebook.domain.event.dto.request;

import lombok.Getter;

import java.util.List;

@Getter
public class AdminEventDeleteRequestDTO {
    private List<Long> eventId;
}

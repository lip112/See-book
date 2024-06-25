package com.example.seebook.domain.event.dto.response;

import com.example.seebook.domain.event.dto.AdminEventListDTO;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class AdminEventListResponseDTO {
    private Long totalEventCount;
    private Long endPage;
    private List<AdminEventListDTO> event;

    @Builder
    public AdminEventListResponseDTO(Long totalEventCount, Long endPage, List<AdminEventListDTO> event) {
        this.totalEventCount = totalEventCount;
        this.endPage = endPage;
        this.event = event;
    }
}

package com.example.seebook.domain.event.repository;


import com.example.seebook.domain.event.dto.response.AdminEventDetailResponseDTO;
import com.example.seebook.domain.event.dto.response.AdminEventListResponseDTO;

public interface EventRepositoryCustom {
    AdminEventListResponseDTO getAdminEventList(int offset, int limit, String query, String queryType);

    AdminEventDetailResponseDTO getAdminEventDetail(Long eventId);
}

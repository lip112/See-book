package com.example.seebook.domain.support.repository;

import com.example.seebook.domain.support.domain.SupportType;
import com.example.seebook.domain.support.dto.response.AdminSupportListResponseDTO;
import com.example.seebook.domain.support.dto.response.SupportListResponseDTO;

public interface SupportRepositoryCustom {
    SupportListResponseDTO getList(Long userId, int offset, int limit);

    AdminSupportListResponseDTO getAdminList(int offset, int limit,String query, String queryType);
}

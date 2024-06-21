package com.example.seebook.domain.profile.repository;

import com.example.seebook.domain.profile.dto.request.ProfileReviewListRequestDTO;
import com.example.seebook.domain.profile.dto.response.JoinResponseDTO;
import com.example.seebook.domain.profile.dto.response.ProfileReviewListResponseDTO;

public interface ProfileRepositoryCustom {
    ProfileReviewListResponseDTO getProfileWithReviewListDTO(Long userId, int offset, int limit);

    JoinResponseDTO joinProfile(Long userId);
}

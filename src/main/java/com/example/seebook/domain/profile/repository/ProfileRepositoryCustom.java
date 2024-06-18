package com.example.seebook.domain.profile.repository;

import com.example.seebook.domain.profile.dto.request.ProfileWithReviewListRequestDTO;
import com.example.seebook.domain.profile.dto.response.JoinResponseDTO;

public interface ProfileRepositoryCustom {
    void getProfileWithReviewListDTO(ProfileWithReviewListRequestDTO profileWithReviewListRequestDTO);

    JoinResponseDTO joinProfile(Long userId);
}
